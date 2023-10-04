package com.alijaver.myapp;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class TodoView extends VerticalLayout {

    private TodoRepo repo;
    TextField campoTarefa = new TextField();
    Button botaoAdicionar = new Button("Adicionar");
    VerticalLayout listaTarefas = new VerticalLayout();
    Button botaoLimparConcluidas = new Button("Limpar tarefas concluídas");
    Button botaoLimparTodas = new Button("Limpar todas as tarefas");

    public TodoView(TodoRepo repo) {
        this.repo = repo;

        add(
                new H1("Tarefas importantes:"),
                new HorizontalLayout(campoTarefa, botaoAdicionar),
                listaTarefas,
                botaoLimparConcluidas,
                botaoLimparTodas
        );

        botaoAdicionar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botaoAdicionar.addClickShortcut(Key.ENTER);
        botaoAdicionar.addClickListener(e -> {
            repo.save(new Todo(campoTarefa.getValue()));
            campoTarefa.clear();
            campoTarefa.focus();

            atualizarTarefas();
        });

        botaoLimparConcluidas.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        botaoLimparConcluidas.addClickListener(e -> {
            repo.deleteByConcluido(true);
            atualizarTarefas();
        });

        botaoLimparTodas.addThemeVariants(ButtonVariant.LUMO_ERROR);
        botaoLimparTodas.addClickListener(e -> {
            repo.deleteAll();
            atualizarTarefas();
        });

        atualizarTarefas();
    }

    private void atualizarTarefas() {
        listaTarefas.removeAll();

        repo.findAll()
                .stream()
                .map(TarefaLayout::new)
                .forEach(listaTarefas::add);
    }

    class TarefaLayout extends HorizontalLayout {
        Checkbox concluida = new Checkbox();
        TextField tarefa = new TextField();

        public TarefaLayout(Todo todo) {
            add(concluida, tarefa);
            setDefaultVerticalComponentAlignment(Alignment.CENTER);

            Binder<Todo> binder = new Binder<>(Todo.class);
            binder.bindInstanceFields(this);
            binder.setBean(todo);

            binder.addValueChangeListener(e -> {
                repo.save(binder.getBean());
            });
        }
    }
}
