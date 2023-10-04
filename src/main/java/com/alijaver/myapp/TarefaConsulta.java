package com.alijaver.myapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaConsulta {
    public List<Todo> obterTodasTarefas() {
        String url = "jdbc:mysql://localhost:3306/todo_list";
        String usuario = "seu_usuario";
        String senha = "sua_senha";
        List<Todo> tarefas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM tasks";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Todo tarefa = new Todo();
                        tarefa.setId((long) rs.getInt("id"));
                        tarefa.setDescricao(rs.getString("task"));
                        tarefa.setConcluida(rs.getBoolean("done"));
                        tarefas.add(tarefa);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tarefas;
    }
}
