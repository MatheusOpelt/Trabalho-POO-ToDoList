package com.alijaver.myapp;

import com.vaadin.flow.theme.AbstractTheme;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.parallel.ParallelTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Classe base para Testes de Integração do TestBench no Chrome.
 * <p>
 * Os testes utilizam o driver do Chrome (consulte o pom.xml para o perfil de
 * integration-tests) para executar testes de integração em um Chrome em modo
 * headless. Se a propriedade {@code test.use.hub} estiver definida como true,
 * {@code AbstractViewTest} assumirá que o teste do TestBench está sendo
 * executado em um ambiente de integração contínua (CI). Para manter esta classe
 * leve, ela faz algumas suposições sobre o ambiente de CI (como variáveis de
 * ambiente disponíveis). Não é aconselhável usar esta classe como base para seus
 * próprios testes do TestBench.
 * <p>
 * Para saber mais sobre o TestBench, visite
 * <a href="https://vaadin.com/docs/v10/testbench/testbench-overview.html">Vaadin TestBench</a>.
 */
public abstract class AbstractViewTest extends ParallelTest {
    private static final int PORTA_SERVIDOR = 8080;

    private final String rota;
    private final By seletorRaiz;

    @Rule
    public ScreenshotOnFailureRule regraCapturaTela = new ScreenshotOnFailureRule(this,
            false);

    public AbstractViewTest() {
        this("", By.tagName("body"));
    }

    protected AbstractViewTest(String rota, By seletorRaiz) {
        this.rota = rota;
        this.seletorRaiz = seletorRaiz;
    }

    @BeforeClass
    public static void configurarClasse() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void configurar() throws Exception {
        if (isUsandoHub()) {
            super.setup();
        } else {
            setDriver(TestBench.createDriver(new ChromeDriver()));
        }
        getDriver().get(getURL(rota));
    }

    /**
     * Método de conveniência para obter o elemento raiz da visualização com base
     * no seletor passado para o construtor.
     *
     * @return o elemento raiz
     */
    protected WebElement getElementoRaiz() {
        return findElement(seletorRaiz);
    }

    /**
     * Afirma que o {@code elemento} fornecido é renderizado usando um tema
     * identificado por {@code themeClass}. Se o tema não for encontrado, a asserção
     * do JUnit falhará no caso de teste.
     *
     * @param elemento    elemento da web a ser verificado quanto ao tema
     * @param themeClass  classe do tema (como {@code Lumo.class})
     */
    protected void assertTemaPresenteNoElemento(
            WebElement elemento, Class<? extends AbstractTheme> themeClass) {
        String nomeTema = themeClass.getSimpleName().toLowerCase();
        Boolean possuiEstilo = (Boolean) executeScript("" +
                "var estilos = Array.from(arguments[0]._template.content" +
                ".querySelectorAll('style'))" +
                ".filter(estilo => estilo.textContent.indexOf('" +
                nomeTema + "') > -1);" +
                "return estilos.length > 0;", elemento);

        Assert.assertTrue("O elemento '" + elemento.getTagName() + "' deveria ter" +
                        " o tema '" + themeClass.getSimpleName() + "'.",
                possuiEstilo);
    }

    /**
     * Propriedade definida como true quando em execução em um hub de testes.
     */
    private static final String PROPRIEDADE_USAR_HUB = "test.use.hub";

    /**
     * Retorna o nome do host de implantação concatenado com a rota.
     *
     * @return URL da rota
     */
    private static String getURL(String rota) {
        return String.format("http://%s:%d/%s", getHostnameImplantacao(),
                PORTA_SERVIDOR, rota);
    }

    /**
     * Retorna se estamos usando um hub de testes. Isso significa que o starter
     * está executando testes no ambiente de integração contínua da Vaadin e usa o
     * TestBench para se conectar ao hub de testes.
     *
     * @return se estamos usando um hub de testes
     */
    private static boolean isUsandoHub() {
        return Boolean.TRUE.toString().equals(
                System.getProperty(PROPRIEDADE_USAR_HUB));
    }

    /**
     * Se em execução no CI, obtém o nome do host da variável de ambiente HOSTNAME.
     *
     * @return o nome do host
     */
    private static String getHostnameImplantacao() {
        return isUsandoHub() ? System.getenv("HOSTNAME") : "localhost";
    }
}

