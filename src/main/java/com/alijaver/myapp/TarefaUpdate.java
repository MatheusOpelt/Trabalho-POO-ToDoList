package com.alijaver.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TarefaUpdate {
    // Método para marcar uma tarefa como concluída ou não no banco de dados
    public void atualizarTarefa(int id, boolean concluida) {
        String url = "jdbc:mysql://localhost:3306/todo_list";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "UPDATE tasks SET done = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setBoolean(1, concluida);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
