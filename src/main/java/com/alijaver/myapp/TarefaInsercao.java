package com.alijaver.myapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TarefaInsercao {
    // Método para adicionar uma nova tarefa ao banco de dados
    public void adicionarTarefa(String descricao) {
        String url = "jdbc:mysql://localhost:3306/todo_list";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO tasks (task, done) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, descricao);
                pstmt.setBoolean(2, false); // Define a tarefa como não concluída
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
