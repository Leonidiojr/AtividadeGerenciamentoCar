package controller;

import static application.MainApp.getConnection;
import model.ItensServicos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensServicosController {

    // Método para criar um novo registro na tabela ItensServicos
    public void criarItensServicos(ItensServicos itensServicos) {
        String sql = "INSERT INTO ItensServicos (boxServico, dataInicio, dataFinalizado, qntd, idServicoFK, precoUnit, idOS) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itensServicos.getBoxServico());
            stmt.setDate(2, new java.sql.Date(itensServicos.getDataInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(itensServicos.getDataFinalizado().getTime()));
            stmt.setInt(4, itensServicos.getQntd());
            stmt.setInt(5, itensServicos.getIdServicoFK());
            stmt.setDouble(6, itensServicos.getPrecoUnit());
            stmt.setInt(7, itensServicos.getIdOs()); // Adiciona o ID da OS
            stmt.executeUpdate();
            System.out.println("Itens de Serviços criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar itens de serviços: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela ItensServicos pelo ID
    public ItensServicos buscarItensServicos(int idItensServPK) {
        ItensServicos itensServicos = null;
        String sql = "SELECT * FROM ItensServicos WHERE idItensServPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idItensServPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    itensServicos = new ItensServicos(
                        rs.getInt("iDItensServPK"),
                        rs.getInt("boxServico"),
                        rs.getDate("dataInicio"),
                        rs.getDate("dataFinalizado"),
                        rs.getInt("qntd"),
                        rs.getInt("idServicoFK"),
                        rs.getDouble("precoUnit"),
                        rs.getInt("idOS") // Adiciona o ID da OS
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar itens de serviços: " + e.getMessage());
        }
        return itensServicos;
    }

    // Método para listar todos os registros da tabela ItensServicos
    public List<ItensServicos> listarItensServicos() {
        List<ItensServicos> itensServicosList = new ArrayList<>();
        String sql = "SELECT * FROM ItensServicos";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItensServicos itensServicos = new ItensServicos(
                    rs.getInt("idItensServPK"),
                    rs.getInt("boxServico"),
                    rs.getDate("dataInicio"),
                    rs.getDate("dataFinalizado"),
                    rs.getInt("qntd"),
                    rs.getInt("idServicoFK"),
                    rs.getDouble("precoUnit"),
                    rs.getInt("idOS") // Adiciona o ID da OS
                );
                itensServicosList.add(itensServicos);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens de serviços: " + e.getMessage());
        }
        return itensServicosList;
    }

    // Método para atualizar um registro na tabela ItensServicos
    public void atualizarItensServicos(ItensServicos itensServicos) {
        String sql = "UPDATE ItensServicos SET boxServico = ?, dataInicio = ?, dataFinalizado = ?, qntd = ?, idServicoFK = ?, precoUnit = ?, idOS = ? WHERE idItensServPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itensServicos.getBoxServico());
            stmt.setDate(2, new java.sql.Date(itensServicos.getDataInicio().getTime()));
            stmt.setDate(3, new java.sql.Date(itensServicos.getDataFinalizado().getTime()));
            stmt.setInt(4, itensServicos.getQntd());
            stmt.setInt(5, itensServicos.getIdServicoFK());
            stmt.setDouble(6, itensServicos.getPrecoUnit());
            stmt.setInt(7, itensServicos.getIdOs()); // Adiciona o ID da OS
            stmt.setInt(8, itensServicos.getIdItensServPK());
            stmt.executeUpdate();
            System.out.println("Itens de Serviços atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar itens de serviços: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela ItensServicos pelo ID
    public void deletarItensServicos(int idItensServPK) {
        String sql = "DELETE FROM ItensServicos WHERE idItensServPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idItensServPK);
            stmt.executeUpdate();
            System.out.println("Itens de Serviços deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar itens de serviços: " + e.getMessage());
        }
    }

    // Método para buscar itens de serviços por ID da ordem de serviço
    public List<ItensServicos> buscarItensServicosPorOrdemServico(int ordemServicoId) {
        List<ItensServicos> itensServicosList = new ArrayList<>();
        String sql = "SELECT i.iDItensServPK, i.boxServico, i.dataInicio, i.dataFinalizado, i.qntd, i.idServicoFK, i.precoUnit, s.descricao, i.idOS " +
                     "FROM ItensServicos i " +
                     "JOIN Servico s ON i.idServicoFK = s.iDServicoPK " +
                     "WHERE i.idOS = ?";  // Corrigido para i.idOS

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItensServicos itemServico = new ItensServicos(
                        rs.getInt("iDItensServPK"),
                        rs.getInt("boxServico"),
                        rs.getDate("dataInicio"),
                        rs.getDate("dataFinalizado"),
                        rs.getInt("qntd"),
                        rs.getInt("idServicoFK"),
                        rs.getDouble("precoUnit"),
                        rs.getInt("idOS") // Adiciona o ID da OS
                    );
                    itensServicosList.add(itemServico);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Loga a exceção para depuração
        }
        return itensServicosList;
    }
    
}
