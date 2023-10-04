package com.alijaver.myapp;

import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainViewIT extends AbstractViewTest {

    @Test
    public void clicarNoBotaoMostraNotificacao() {
        Assert.assertFalse($(NotificationElement.class).exists());
        $(ButtonElement.class).first().click();
        Assert.assertTrue($(NotificationElement.class).waitForFirst().isOpen());
    }

    @Test
    public void clicarNoBotaoDuasVezesMostraDuasNotificacoes() {
        Assert.assertFalse($(NotificationElement.class).exists());
        ButtonElement botao = $(ButtonElement.class).first();
        botao.click();
        botao.click();
        Assert.assertEquals(2, $(NotificationElement.class).all().size());
    }

    @Test
    public void botaoUsaTemaLumo() {
        WebElement elemento = $(ButtonElement.class).first();
        assertTemaPresenteNoElemento(elemento, Lumo.class);
    }

    @Test
    public void testClicarNoBotaoMostraNotificacaoOlaUsuarioAnonimoQuandoNomeDeUsuarioEstiverVazio() {
        ButtonElement botao = $(ButtonElement.class).first();
        botao.click();
        Assert.assertTrue($(NotificationElement.class).exists());
        NotificationElement notificacao = $(NotificationElement.class).first();
        Assert.assertEquals("Olá usuário anônimo", notificacao.getText());
    }

    @Test
    public void testClicarNoBotaoMostraNotificacaoOlaUsuarioQuandoUsuarioNaoEstiverVazio() {
        TextFieldElement campoTexto = $(TextFieldElement.class).first();
        campoTexto.setValue("Vaadiner");
        ButtonElement botao = $(ButtonElement.class).first();
        botao.click();
        Assert.assertTrue($(NotificationElement.class).exists());
        NotificationElement notificacao = $(NotificationElement.class).first();
        Assert.assertEquals("Olá Vaadiner", notificacao.getText());
    }

    @Test
    public void testEnterMostraNotificacaoOlaUsuarioQuandoUsuarioNaoEstiverVazio() {
        TextFieldElement campoTexto = $(TextFieldElement.class).first();
        campoTexto.setValue("Vaadiner");
        campoTexto.sendKeys(Keys.ENTER);
        Assert.assertTrue($(NotificationElement.class).exists());
        NotificationElement notificacao = $(NotificationElement.class).first();
        Assert.assertEquals("Olá Vaadiner", notificacao.getText());
    }
}
