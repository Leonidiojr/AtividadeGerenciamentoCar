package controller;

import static application.MainApp.getConnection;
import model.Servico;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicoController {

    // Método para criar um novo registro na tabela Servico
    public void criarServico(Servico servico) {
        String sql = "INSERT INTO Servico (descricao, qntd, precoUnit) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setInt(2, servico.getQntd());
            stmt.setBigDecimal(3, servico.getPrecoUnit());
            stmt.executeUpdate();
            System.out.println("Serviço criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar serviço: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Servico pelo ID
    public Servico buscarServico(int idServicoPK) {
        Servico servico = null;
        String sql = "SELECT * FROM Servico WHERE idServicoPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServicoPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servico = new Servico(
                        rs.getInt("idServicoPK"),
                        rs.getString("descricao"),
                        rs.getInt("qntd"),
                        rs.getBigDecimal("precoUnit")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço: " + e.getMessage());
        }
        return servico;
    }

    // Método para listar todos os registros da tabela Servico
    public List<Servico> listarServicos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico ORDER BY descricao";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getInt("idServicoPK"),
                    rs.getString("descricao"),
                    rs.getInt("qntd"),
                    rs.getBigDecimal("precoUnit")
                );
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }
        return servicos;
    }

    // Método para atualizar um registro na tabela Servico
    public void atualizarServico(Servico servico) {
        String sql = "UPDATE Servico SET descricao = ?, qntd = ?, precoUnit = ? WHERE idServicoPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setInt(2, servico.getQntd());
            stmt.setBigDecimal(3, servico.getPrecoUnit());
            stmt.setInt(4, servico.getIdServicoPK());
            stmt.executeUpdate();
            System.out.println("Serviço atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Servico pelo ID
    public void deletarServico(int idServicoPK) {
        String sql = "DELETE FROM Servico WHERE idServicoPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServicoPK);
            stmt.executeUpdate();
            System.out.println("Serviço deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço: " + e.getMessage());
        }
    }

    // Método para pesquisar serviços com base em vários critérios
    public List<Servico> pesquisarServicos(String valorPesquisa) {
        List<Servico> servicosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM Servico WHERE (descricao LIKE ?) ORDER BY descricao";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";
            pstmt.setString(1, pesquisa);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getInt("idServicoPK"),
                    rs.getString("descricao"),
                    rs.getInt("qntd"),
                    rs.getBigDecimal("precoUnit")
                );
                servicosEncontrados.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar serviços: " + e.getMessage());
        }
        return servicosEncontrados;
    }
}
