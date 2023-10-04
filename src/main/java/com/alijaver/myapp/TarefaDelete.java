package com.alijaver.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TarefaDelete {
    // Método para excluir uma tarefa do banco de dados
    public void excluirTarefa(int id) {
        String url = "jdbc:mysql://localhost:3306/todo_list";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "DELETE FROM tasks WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
