package controller;

import static application.MainApp.getConnection;
import model.Acessorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessorioController {

    // Método para criar um novo registro na tabela Acessorio
    public void criarAcessorio(Acessorio acessorio) {
        String sql = "INSERT INTO Acessorio (descricao) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, acessorio.getDescricao());
            stmt.executeUpdate();
            System.out.println("Acessório criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar acessório: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Acessorio pelo ID
    public Acessorio buscarAcessorio(int idAcessorioPK) {
        Acessorio acessorio = null;
        String sql = "SELECT * FROM Acessorio WHERE idAcessorioPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAcessorioPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    acessorio = new Acessorio(
                        rs.getInt("idAcessorioPK"),
                        rs.getString("descricao")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar acessório: " + e.getMessage());
        }
        return acessorio;
    }

    // Método para listar todos os registros da tabela Acessorio
    public List<Acessorio> listarAcessorios() {
        List<Acessorio> acessorios = new ArrayList<>();
        String sql = "SELECT * FROM Acessorio";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Acessorio acessorio = new Acessorio(
                    rs.getInt("idAcessorioPK"),
                    rs.getString("descricao")
                );
                acessorios.add(acessorio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar acessórios: " + e.getMessage());
        }
        return acessorios;
    }

    // Método para atualizar um registro na tabela Acessorio
    public void atualizarAcessorio(Acessorio acessorio) {
        String sql = "UPDATE Acessorio SET descricao = ? WHERE idAcessorioPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, acessorio.getDescricao());
            stmt.setInt(2, acessorio.getIdAcessorioPK());
            stmt.executeUpdate();
            System.out.println("Acessório atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar acessório: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Acessorio pelo ID
    public void deletarAcessorio(int idAcessorioPK) {
        String sql = "DELETE FROM Acessorio WHERE idAcessorioPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAcessorioPK);
            stmt.executeUpdate();
            System.out.println("Acessório deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar acessório: " + e.getMessage());
        }
    }

    
}
