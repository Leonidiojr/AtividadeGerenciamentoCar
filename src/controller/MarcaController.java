package controller;

import static application.MainApp.getConnection;
import model.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaController {

  
    // Método para criar um novo registro na tabela Marca
    public void criarMarca(Marca marca) {
        String sql = "INSERT INTO Marca (nome, tipo, logo, idModeloFK) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getTipo());
            stmt.setBytes(3, marca.getLogo());
            stmt.setInt(4, marca.getIdModeloFK());
            stmt.executeUpdate();
            System.out.println("Marca criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar marca: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Marca pelo ID
    public Marca buscarMarca(int idMarcaPK) {
        Marca marca = null;
        String sql = "SELECT * FROM Marca WHERE idMarcaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMarcaPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    marca = new Marca(
                        rs.getInt("idMarcaPK"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getBytes("logo"),
                        rs.getInt("idModeloFK")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar marca: " + e.getMessage());
        }
        return marca;
    }

    // Método para listar todos os registros da tabela Marca
    public List<Marca> listarMarcas() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM Marca ORDER BY nome";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Marca marca = new Marca(
                    rs.getInt("idMarcaPK"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getBytes("logo"),
                    rs.getInt("idModeloFK")
                );
                marcas.add(marca);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar marcas: " + e.getMessage());
        }
        return marcas;
    }

    // Método para atualizar um registro na tabela Marca
    public void atualizarMarca(Marca marca) {
        String sql = "UPDATE Marca SET nome = ?, tipo = ?, logo = ?, idModeloFK = ? WHERE idMarcaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getTipo());
            stmt.setBytes(3, marca.getLogo());
            stmt.setInt(4, marca.getIdModeloFK());
            stmt.setInt(5, marca.getIdMarcaPK());
            stmt.executeUpdate();
            System.out.println("Marca atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar marca: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Marca pelo ID
    public void deletarMarca(int idMarcaPK) {
        String sql = "DELETE FROM Marca WHERE idMarcaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMarcaPK);
            stmt.executeUpdate();
            System.out.println("Marca deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar marca: " + e.getMessage());
        }
    }
    
   
    // Método para pesquisar marcas com base em vários critérios
    public List<Marca> pesquisarMarcas(String valorPesquisa) {
        List<Marca> marcasEncontradas = new ArrayList<>();
        String sql = "SELECT * FROM Marca WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";
            
            // Define o parâmetro da consulta
            pstmt.setString(1, pesquisa);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca(
                        rs.getInt("idMarcaPK"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getBytes("logo"),
                        rs.getInt("idModeloFK")
                );
                marcasEncontradas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marcasEncontradas;
    }

    // Método para obter o maior valor de idMarcaPK
    public int getMaxIdMarcaPK() {
        String sql = "SELECT MAX(idMarcaPK) FROM Marca";
        int maxId = -1;

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter o maior valor de idMarcaPK: " + e.getMessage());
        }

        return maxId;

}

   
}
