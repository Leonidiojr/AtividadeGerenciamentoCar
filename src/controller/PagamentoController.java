package controller;

import static application.MainApp.getConnection;
import application.Constants;
import model.Pagamento;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoController {

    public void criarPagamento(Pagamento pagamento) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO pagamento (dataPagamento, valorPago, tipoPagamento, deslocamento, servicoGuincho) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setObject(1, pagamento.getDataPagamento());
                stmt.setDouble(2, pagamento.getValorPago());
                stmt.setString(3, pagamento.getTipoPagamento());
                stmt.setDouble(4, pagamento.getDeslocamento());
                stmt.setDouble(5, pagamento.getServicoGuincho());
                stmt.executeUpdate();
                System.out.println("Pagamento criado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Pagamento buscarPagamento(int idPagamento) {
        Pagamento pagamento = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pagamento WHERE idPagamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPagamento);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        pagamento = new Pagamento(
                            rs.getInt("idPagamento"),
                            rs.getTimestamp("dataPagamento").toLocalDateTime(),
                            rs.getInt("numeroOrdemServico"),
                            rs.getDouble("valorPago"),
                            rs.getString("tipoPagamento"),
                            rs.getDouble("deslocamento"),
                            rs.getDouble("servicoGuincho")
                        );
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pagamento;
    }

    public List<Pagamento> listarPagamentos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pagamento";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pagamento pagamento = new Pagamento(
                            rs.getInt("idPagamento"),
                            rs.getTimestamp("dataPagamento").toLocalDateTime(),
                            rs.getInt("numeroOrdemServico"),
                            rs.getDouble("valorPago"),
                            rs.getString("tipoPagamento"),
                            rs.getDouble("deslocamento"),
                            rs.getDouble("servicoGuincho")
                        );
                        pagamentos.add(pagamento);
                    }
                    
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pagamentos;
    }

    // Método para atualizar um pagamento
    public void atualizarPagamento(Pagamento pagamento) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Pagamento SET dataPagamento = ?, valorPago = ?, tipoPagamento = ?, deslocamento = ?, servicoGuincho = ? WHERE idPagamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setObject(1, pagamento.getDataPagamento());
                stmt.setDouble(2, pagamento.getValorPago());
                stmt.setString(3, pagamento.getTipoPagamento());
                stmt.setDouble(4, pagamento.getDeslocamento());
                stmt.setDouble(5, pagamento.getServicoGuincho());
                stmt.setInt(6, pagamento.getIdPagamento());
                stmt.executeUpdate();
                System.out.println("Pagamento atualizado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
    // Método para deletar um pagamento
    public void deletarPagamento(int idPagamento) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Pagamento WHERE idPagamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPagamento);
                stmt.executeUpdate();
                System.out.println("Pagamento deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para buscar pagamentos por ID da ordem de serviço
    public List<Pagamento> buscarPagamentosPorOrdemServico(int ordemServicoId) {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT idPagamento, dataPagamento, numeroOrdemServico, valorPago, tipoPagamento, deslocamento, servicoGuincho " +
                     "FROM Pagamento WHERE numeroOrdemServico = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pagamento pagamento = new Pagamento(
                        rs.getInt("idPagamento"),
                        rs.getTimestamp("dataPagamento").toLocalDateTime(),
                        rs.getInt("numeroOrdemServico"),
                        rs.getDouble("valorPago"),
                        rs.getString("tipoPagamento"),
                        rs.getDouble("deslocamento"),
                        rs.getDouble("servicoGuincho")
                    );
                    pagamentos.add(pagamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Loga a exceção para depuração
        }
        return pagamentos;
    }
    
    
    
}
