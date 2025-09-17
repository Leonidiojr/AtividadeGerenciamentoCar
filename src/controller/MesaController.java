package controller;

import controller.enums.StatusOS;
import static application.MainApp.getConnection;

import application.Constants;
import model.Box;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaController {

    public void criarMesa(Box mesa) {
        String sql = "INSERT INTO mesa (numero_mesa, status_mesa) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesa.getNumeroMesa());
            stmt.setString(2, mesa.getStatusMesa().name()); // Salva o nome do enum no banco de dados
            stmt.executeUpdate();
            System.out.println("Mesa criada com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    public Box buscarMesa(int numeroMesa) {
        Box mesa = null;
        String sql = "SELECT * FROM mesa WHERE numero_mesa = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroMesa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mesa = new Box(
                            rs.getInt("numero_mesa"),
                            StatusOS.valueOf(rs.getString("status_mesa")) // Converte o nome do enum para o tipo StatusOS
                    );
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return mesa;
    }

    public List<Box> listarMesas() {
        List<Box> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesa ORDER BY numero_mesa";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Box mesa = new Box(
                        rs.getInt("numero_mesa"),
                        StatusOS.valueOf(rs.getString("status_mesa")) // Converte o nome do enum para o tipo StatusOS
                );
                mesas.add(mesa);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return mesas;
    }

    public int listarMesasLivres() {
        int mesasLivres = 0;
        String sql = "SELECT * FROM mesa WHERE status_mesa = 'LIVRE'";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mesasLivres++;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return mesasLivres;
    }

    public void atualizarMesa(Box mesa) {
        String sql = "UPDATE mesa SET status_mesa = ? WHERE numero_mesa = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mesa.getStatusMesa().name()); // Salva o nome do enum no banco de dados
            stmt.setInt(2, mesa.getNumeroMesa());
            stmt.executeUpdate();
            System.out.println("Mesa atualizada com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    public void deletarMesa(int numeroMesa) {
        String sql = "DELETE FROM mesa WHERE numero_mesa = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroMesa);
            stmt.executeUpdate();
            System.out.println("Mesa deletada com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

}
