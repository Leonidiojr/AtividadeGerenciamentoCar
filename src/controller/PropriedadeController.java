package controller;

import static application.MainApp.getConnection;
import model.Propriedade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeController {

    // Método para criar um novo registro na tabela Propriedade
    public void criarPropriedade(Propriedade propriedade) {
        String sql = "INSERT INTO Propriedade (idClienteFK, placaFK) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propriedade.getIdClienteFK());
            stmt.setString(2, propriedade.getPlacaFK());
            stmt.executeUpdate();
            System.out.println("Propriedade criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar propriedade: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Propriedade pelo ID do Cliente e Placa
    public Propriedade buscarPropriedade(int idClienteFK, String placaFK) {
        Propriedade propriedade = null;
        String sql = "SELECT * FROM Propriedade WHERE idClienteFK = ? AND placaFK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClienteFK);
            stmt.setString(2, placaFK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    propriedade = new Propriedade(
                        rs.getInt("idClienteFK"),
                        rs.getString("placaFK")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar propriedade: " + e.getMessage());
        }
        return propriedade;
    }
    
    
    // Método para buscar um registro na tabela Propriedade pelo ID do Cliente e Placa
    public Propriedade buscarPropriedadeporPlaca(String placaFK) {
    
        Propriedade propriedade = null;
        String sql = "SELECT * FROM Propriedade WHERE placaFK = ? ORDER BY datahoracadastro DESC;";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placaFK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    propriedade = new Propriedade(
                        rs.getInt("idClienteFK"),
                        rs.getString("placaFK")
                    );
                    System.out.println(propriedade.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar propriedade: " + e.getMessage());
        }
        return propriedade;
    }
    

    // Método para listar todos os registros da tabela Propriedade
    public List<Propriedade> listarPropriedades() {
        List<Propriedade> propriedades = new ArrayList<>();
        String sql = "SELECT * FROM Propriedade ORDER BY placaFK";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Propriedade propriedade = new Propriedade(
                    rs.getInt("idClienteFK"),
                    rs.getString("placaFK")
                );
                propriedades.add(propriedade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar propriedades: " + e.getMessage());
        }
        return propriedades;
    }

    // Método para deletar um registro na tabela Propriedade pelo ID do Cliente e Placa
    public void deletarPropriedade(int idClienteFK, String placaFK) {
        String sql = "DELETE FROM Propriedade WHERE idClienteFK = ? AND placaFK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClienteFK);
            stmt.setString(2, placaFK);
            stmt.executeUpdate();
            System.out.println("Propriedade deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar propriedade: " + e.getMessage());
        }
    }

    // Método para atualizar um registro na tabela Propriedade
    public void atualizarPropriedade(Propriedade propriedade) {
        String sql = "UPDATE Propriedade SET placaFK = ? WHERE idClienteFK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, propriedade.getPlacaFK());
            stmt.setInt(2, propriedade.getIdClienteFK());
            stmt.executeUpdate();
            System.out.println("Propriedade atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar propriedade: " + e.getMessage());
        }
    }    
    
}
