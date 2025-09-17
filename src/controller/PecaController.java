package controller;

import static application.MainApp.getConnection;
import model.Peca;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaController {

    // Método para criar um novo registro na tabela Pecas
    public void criarPeca(Peca peca) {
        String sql = "INSERT INTO Pecas (descricao, codFabricante, qntdEstoque, precoUnit, idItensFK) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca.getDescricao());
            stmt.setString(2, peca.getCodFabricante());
            stmt.setInt(3, peca.getQntdEstoque());
            stmt.setBigDecimal(4, peca.getPrecoUnit());
            stmt.setInt(5, peca.getIdItensFK());
            stmt.executeUpdate();
            System.out.println("Peça criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar peça: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Pecas pelo ID
    public Peca buscarPeca(int idPecasPK) {
        Peca peca = null;
        String sql = "SELECT * FROM Pecas WHERE iDPecasPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPecasPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    peca = new Peca(
                        rs.getInt("iDPecasPK"),
                        rs.getString("descricao"),
                        rs.getString("codFabricante"),
                        rs.getInt("qntdEstoque"),                            
                        rs.getBigDecimal("precoUnit"),
                        rs.getInt("iDItensFK")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar peça: " + e.getMessage());
        }
        return peca;
    }

    // Método para listar todos os registros da tabela Pecas
    public List<Peca> listarPecas() {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM Pecas ORDER BY descricao";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Peca peca = new Peca(
                    rs.getInt("iDPecasPK"),
                    rs.getString("descricao"),
                    rs.getString("codFabricante"),
                    rs.getInt("qntdEstoque"),
                    rs.getBigDecimal("precoUnit"),
                    rs.getInt("iDItensFK")
                );
                pecas.add(peca);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar peças: " + e.getMessage());
        }
        return pecas;
    }

    // Método para atualizar um registro na tabela Pecas
    public void atualizarPeca(Peca peca) {
        String sql = "UPDATE Pecas SET descricao = ?, codFabricante = ?, qntdEstoque = ?, precoUnit = ?, iDItensFK = ? WHERE iDPecasPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca.getDescricao());
            stmt.setString(2, peca.getCodFabricante());
            stmt.setInt(3, peca.getQntdEstoque());
            stmt.setBigDecimal(4, peca.getPrecoUnit());
            stmt.setInt(5, peca.getIdItensFK());
            stmt.setInt(6, peca.getIdPecasPK());
            stmt.executeUpdate();
            System.out.println(stmt.toString());
            System.out.println("Peça atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Pecas pelo ID
    public void deletarPeca(int idPecasPK) {
        String sql = "DELETE FROM Pecas WHERE iDPecasPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPecasPK);
            stmt.executeUpdate();
            System.out.println("Peça deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar peça: " + e.getMessage());
        }
    }
    public List<Peca> pesquisarPecas(String valorPesquisa) {
    List<Peca> pecasEncontradas = new ArrayList<>();
    String sql = "SELECT * FROM Pecas WHERE (descricao LIKE ? OR codFabricante LIKE ?) ORDER BY descricao";

    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        String pesquisa = "%" + valorPesquisa + "%";

        // Define os parâmetros da consulta
        pstmt.setString(1, pesquisa);
        pstmt.setString(2, pesquisa);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Peca peca = new Peca(
                rs.getInt("iDPecasPK"),
                rs.getString("descricao"),
                rs.getString("codFabricante"),
                rs.getInt("qntdEstoque"),                            
                rs.getBigDecimal("precoUnit"),
                rs.getInt("iDItensFK")
            );
            pecasEncontradas.add(peca);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao pesquisar peças: " + e.getMessage());
    }
    return pecasEncontradas;
}

    
}
