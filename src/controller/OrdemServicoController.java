package controller;

import static application.MainApp.clienteController;
import static application.MainApp.funcionarioController;
import controller.enums.StatusOS;
import static application.MainApp.getConnection;
import static application.MainApp.veiculoController;

import model.OrdemServico;
import model.Cliente;
import model.Veiculo;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrdemServicoController {

    // Método para criar um novo registro na tabela OrdemServico
    public void criarOrdemServico(OrdemServico os) {
        String sql = "INSERT INTO os (dataos, placa, valorfinal, valorpago, status, cliente_id, funcionario_cpf) VALUES (?, ?, ?, ?, ?::statusEnum, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, os.getData());
            stmt.setString(2, os.getPlaca());
            stmt.setBigDecimal(3, os.getValorFinal());
            stmt.setBigDecimal(4, os.getValorPago());
            stmt.setString(5, os.getStatus().name()); // Convertendo enum para string
            stmt.setInt(6, os.getCliente().getIdCliente()); // Adiciona o ID do cliente
            stmt.setString(7, os.getFuncionario().getCPF()); // Adiciona o CPF do funcionário
            stmt.executeUpdate();
            System.out.println("OrdemServico criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    
    // Método para buscar uma OrdemServico pelo ID
    public OrdemServico buscarOrdemServico(int idOrdemServico) {
        OrdemServico os = null;
        String sql = "SELECT * FROM os WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrdemServico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Recupera o status
                    String statusStr = rs.getString("status");
                    StatusOS status = (statusStr != null) ? StatusOS.valueOf(statusStr) : null;

                    // Recupera o cliente, veículo e funcionário
                    Cliente cliente = clienteController.buscarCliente(rs.getInt("cliente_id"));
                    Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(rs.getString("placa"));
                    Funcionario funcionario = funcionarioController.buscarFuncionario(rs.getString("funcionario_cpf"));

                    // Criação da OrdemServico com os dados recuperados
                    os = new OrdemServico(
                            rs.getInt("id"),
                            rs.getDate("dataos").toLocalDate().atStartOfDay(),
                            rs.getString("placa"),
                            rs.getBigDecimal("valorfinal"),
                            rs.getBigDecimal("valorpago"),
                            status,
                            cliente,
                            veiculo,
                            funcionario
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao converter o status: " + e.getMessage());
        }
        return os;
    }

    // Método para listar todas as OrdemServico
    public List<OrdemServico> listarOrdemServico() {
        List<OrdemServico> oss = new ArrayList<>();
        String sql = "SELECT * FROM os";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StatusOS status = (rs.getString("status") != null) ? StatusOS.valueOf(rs.getString("status")) : null;

                // Recupera o cliente, veículo e funcionário
                Cliente cliente = clienteController.buscarCliente(rs.getInt("cliente_id"));
                Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(rs.getString("placa"));
                Funcionario funcionario = funcionarioController.buscarFuncionario(rs.getString("funcionario_cpf"));

                OrdemServico os = new OrdemServico(
                        rs.getInt("id"),
                        rs.getDate("dataos").toLocalDate().atStartOfDay(),
                        rs.getString("placa"),
                        rs.getBigDecimal("valorfinal"),
                        rs.getBigDecimal("valorpago"),
                        status,
                        cliente,
                        veiculo,
                        funcionario
                );
                oss.add(os);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return oss;
    }

    // Método para atualizar uma OrdemServico
    public void atualizarOrdemServico(OrdemServico os) {
        String sql = "UPDATE os SET dataos = ?, placa = ?, valorfinal = ?, valorpago = ?, status = ?, cliente_id = ?, funcionario_cpf = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, os.getData());
            stmt.setString(2, os.getPlaca());
            stmt.setBigDecimal(3, os.getValorFinal());
            stmt.setBigDecimal(4, os.getValorPago());
            stmt.setString(5, os.getStatus().toString());
            stmt.setInt(6, os.getCliente().getIdCliente()); // Atualiza o ID do cliente
            stmt.setString(7, os.getFuncionario().getCPF()); // Atualiza o CPF do funcionário
            stmt.setInt(8, os.getId());
            stmt.executeUpdate();
            System.out.println("OrdemServico atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    // Método para fechar uma OrdemServico
    public void FechaOrdemServico(int idOrdemServico) {
        String sql = "UPDATE os SET status = 'FECHADA' WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrdemServico);
            stmt.executeUpdate();            
            System.out.println("OrdemServico fechada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    // Método para deletar uma OrdemServico
    public void deletarOrdemServico(int idOrdemServico) {
        String sql = "DELETE FROM os WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrdemServico);
            stmt.executeUpdate();
            System.out.println("OrdemServico deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

    // Método para obter o próximo ID de OrdemServico
    public int proximaOrdemServico() {
    
        int quantidadeRegistros = 0;
        String sql = "SELECT COUNT(*) AS quantidade FROM os";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                quantidadeRegistros = rs.getInt("quantidade");
            }
            System.out.println("Consulta executada com sucesso! Quantidade de oss: " + quantidadeRegistros);
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return quantidadeRegistros + 1; // Incrementa para o próximo ID
    }
    
    
    public Cliente obterClientePorDataEId(int osId, LocalDateTime dataOS) {
        Cliente cliente = null;
        String sql = "SELECT c.* FROM Cliente c "
                + "JOIN Propriedade p ON c.iDClientePK = p.iDClienteFK "
                + "JOIN OS o ON p.placaFK = o.placa "
                + "WHERE o.iD = ? AND o.dataOS >= p.dataHoraCadastro AND "
                + "o.dataOS < (SELECT COALESCE(MIN(p2.dataHoraCadastro), TIMESTAMP '9999-12-31 23:59:59') "
                + "FROM Propriedade p2 WHERE p2.placaFK = p.placaFK AND p2.dataHoraCadastro > p.dataHoraCadastro)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, osId);
            stmt.setTimestamp(2, Timestamp.valueOf(dataOS)); // Adicionando a definição do timestamp
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("iDClientePK"),
                            rs.getString("nomeCompleto"),
                            rs.getString("telefone"),
                            rs.getString("ddi"),
                            rs.getString("ddd"),
                            rs.getString("numero"),
                            rs.getString("emailCliente"),
                            rs.getString("endereco"),
                            rs.getString("logradouro"),
                            rs.getString("complemento"),
                            rs.getString("cep"),
                            rs.getInt("tipoPessoa"),
                            rs.getString("CPF"),
                            rs.getDate("dataNascim"),
                            rs.getString("contato"),
                            rs.getString("CNPJ"),
                            rs.getString("IE"),
                            rs.getString("ddi2"),
                            rs.getString("ddd2"),
                            rs.getString("numero2"),
                            rs.getString("telefone2")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Método para pesquisar ordens de serviço com base em vários critérios
    public List<OrdemServico> pesquisarOrdensServicos(String valorPesquisa, String statusPesquisa, String dataPesquisa) {
        List<OrdemServico> ordensEncontradas = new ArrayList<>();
        String sql = "SELECT * FROM os WHERE (placa LIKE ? OR status LIKE ? OR dataos LIKE ?) ORDER BY dataos";        
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";
            String status = "%" + statusPesquisa + "%";
            String data = "%" + dataPesquisa + "%";

            // Define os parâmetros da consulta
            pstmt.setString(1, pesquisa);
            pstmt.setString(2, status);
            pstmt.setString(3, data);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                
                StatusOS statusOs = (rs.getString("status") != null) ? StatusOS.valueOf(rs.getString("status")) : null;
                
                // Recupera o cliente, veículo e funcionário
                Cliente cliente = clienteController.buscarCliente(rs.getInt("cliente_id"));
                Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(rs.getString("placa"));
                Funcionario funcionario = funcionarioController.buscarFuncionario(rs.getString("funcionario_cpf"));

                OrdemServico os = new OrdemServico(
                        rs.getInt("id"),
                        rs.getDate("dataos").toLocalDate().atStartOfDay(),
                        rs.getString("placa"),
                        rs.getBigDecimal("valorfinal"),
                        rs.getBigDecimal("valorpago"),
                        statusOs,
                        cliente,
                        veiculo,
                        funcionario
                );               
                ordensEncontradas.add(os);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordensEncontradas;
    }   
 
        public BigDecimal somarPecasPorOrdemServico(int ordemServicoId) {
        BigDecimal somaTotal = BigDecimal.ZERO;
        String sql = "SELECT precoTotal FROM ItensPecas WHERE idOS = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                somaTotal = somaTotal.add(rs.getBigDecimal("precoTotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somaTotal;
    }
     
        // Função para somar o valor total dos serviços de uma OS
    public BigDecimal somarServicosPorOrdemServico(int ordemServicoId) {
        BigDecimal somaTotal = BigDecimal.ZERO;
        String sql = "SELECT precoTotal FROM ItensServicos WHERE idOS = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                somaTotal = somaTotal.add(rs.getBigDecimal("precoTotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somaTotal;
    }
    
     // Função para somar o valor total dos pagamentos de uma OS
    public BigDecimal somarPagamentosPorOrdemServico(int ordemServicoId) {
        BigDecimal somaTotal = BigDecimal.ZERO;
        String sql = "SELECT valorPago FROM Pagamentos WHERE idOS = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                somaTotal = somaTotal.add(rs.getBigDecimal("valorPago"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somaTotal;
    }

    // Função para somar o valor total dos deslocamentos de uma OS
    public BigDecimal somarDeslocamentoPorOrdemServico(int ordemServicoId) {
        BigDecimal somaTotal = BigDecimal.ZERO;
        String sql = "SELECT valorPago FROM Pagamentos WHERE idOS = ? AND tipoPagamento = 'DESLOCAMENTO'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                somaTotal = somaTotal.add(rs.getBigDecimal("valorPago"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somaTotal;
    }

    // Função para somar o valor total dos serviços de guincho de uma OS
    public BigDecimal somarServicoGuinchoPorOrdemServico(int ordemServicoId) {
        BigDecimal somaTotal = BigDecimal.ZERO;
        String sql = "SELECT valorPago FROM Pagamentos WHERE idOS = ? AND tipoPagamento = 'SERVICO_GUINCHO'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                somaTotal = somaTotal.add(rs.getBigDecimal("valorPago"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somaTotal;
    }

    public BigDecimal somarTotalPorOrdemServico(int ordemServicoId) {

        BigDecimal somaTotal = BigDecimal.ZERO;

        // Soma dos valores das peças
        somaTotal = somaTotal.add(somarPecasPorOrdemServico(ordemServicoId));

        // Soma dos valores dos serviços
        somaTotal = somaTotal.add(somarServicosPorOrdemServico(ordemServicoId));

        // Soma dos valores de deslocamento
        somaTotal = somaTotal.add(somarDeslocamentoPorOrdemServico(ordemServicoId));

        // Soma dos valores de serviço de guincho
        somaTotal = somaTotal.add(somarServicoGuinchoPorOrdemServico(ordemServicoId));

        return somaTotal;
    }
    
 // Método para buscar uma Ordem de Serviço aberta por placa do veículo
    public OrdemServico buscarOrdemServicoAbertaPorPlaca(String placaVeiculo) {
        OrdemServico os = null;
        String sql = "SELECT * FROM OS WHERE placa = ? AND status = 'ABERTA'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placaVeiculo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    os = new OrdemServico(
                            rs.getInt("id"),
                            rs.getTimestamp("dataOS").toLocalDateTime(),
                            rs.getString("placa"),
                            rs.getBigDecimal("valorFinal"),
                            rs.getBigDecimal("valorPago"),
                            StatusOS.valueOf(rs.getString("status")),
                            clienteController.buscarCliente(rs.getInt("cliente_id")),
                            veiculoController.buscarVeiculoPorPlaca(rs.getString("placa")),
                            funcionarioController.buscarFuncionario(rs.getString("funcionario_cpf"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return os;
    }
    
   
   // Método para alterar o status de uma ordem de serviço
    public boolean alterarStatusOrdemServico(int idOrdemServico, String novoStatus) {
        String sql = "UPDATE OS SET status = ?::statusEnum WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idOrdemServico);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status da Ordem de Serviço atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhuma Ordem de Serviço encontrada com o ID fornecido.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o status da Ordem de Serviço: " + e.getMessage());
            return false;
        }
    }
    
}

