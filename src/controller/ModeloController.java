package controller;

import static application.MainApp.getConnection;
import static application.MainApp.marcaAtiva;
import model.Modelo;
import model.OrdemServico;
import controller.enums.StatusOS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloController {

    // Método para criar um novo registro na tabela Modelo
    public void criarModelo(Modelo modelo) {
        String sql = "INSERT INTO Modelo (idModeloPK, nome, idModelo, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, modelo.getIdModeloPK());
            stmt.setString(2, modelo.getNome());
            stmt.setInt(3, modelo.getIdModeloPK());
            stmt.setString(4, modelo.getTipo());
            stmt.executeUpdate();
            System.out.println("Modelo criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar modelo: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Modelo pelo ID
    public Modelo buscarModelo(int idModeloPK) {
    
        Modelo modelo = null;
        String sql = "SELECT * FROM Modelo WHERE idModeloPK = ? AND idMarca=" + marcaAtiva.getIdMarcaPK() + ";";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idModeloPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    modelo = new Modelo(
                        rs.getInt("idModeloPK"),
                        rs.getString("nome"),
                        rs.getInt("idMarca"),
                        rs.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar modelo: " + e.getMessage());
        }
        return modelo;
    }

    // Método para listar todos os registros da tabela Modelo
    public List<Modelo> listarModelos() {
        List<Modelo> modelos = new ArrayList<>();
        String sql = "SELECT * FROM Modelo ORDER BY nome";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Modelo modelo = new Modelo(
                    rs.getInt("idModeloPK"),
                    rs.getString("nome"),
                    rs.getInt("idMarca"),
                    rs.getString("tipo")
                );
                modelos.add(modelo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar modelos: " + e.getMessage());
        }
        return modelos;
    }

    // Método para atualizar um registro na tabela Modelo
    public void atualizarModelo(Modelo modelo) {
        String sql = "UPDATE Modelo SET nome = ?, idMarca = ?, tipo = ? WHERE idModeloPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, modelo.getNome());    // Correto
            stmt.setInt(2, modelo.getIdMarca());    // Correto
            stmt.setString(3, modelo.getTipo());    // Correto
            stmt.setInt(4, modelo.getIdModeloPK()); // Correto - condição WHERE
            stmt.executeUpdate();
            System.out.println("Modelo atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar modelo: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Modelo pelo ID
    public void deletarModelo(int idModeloPK) {
        String sql = "DELETE FROM Modelo WHERE idModeloPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idModeloPK);
            stmt.executeUpdate();
            System.out.println("Modelo deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar modelo: " + e.getMessage());
        }
    }
    
    // Método para pesquisar modelos com base em vários critérios
    public List<Modelo> pesquisarModelos(String valorPesquisa) {
        List<Modelo> modelosEncontradas = new ArrayList<>();
        String sql = "SELECT * FROM Modelo WHERE (nome LIKE ? OR tipo LIKE ?) AND idMarca = ? ORDER BY nome";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";

            // Define os parâmetros da consulta
            pstmt.setString(1, pesquisa);
            pstmt.setString(2, pesquisa);
            pstmt.setInt(3, marcaAtiva.getIdMarcaPK());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo(
                        rs.getInt("idModeloPK"),
                        rs.getString("nome"),
                        rs.getInt("idMarca"),
                        rs.getString("tipo")
                );
                modelosEncontradas.add(modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelosEncontradas;
    }

     

}
