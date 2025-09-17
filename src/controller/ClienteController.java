package controller;

import static application.MainApp.clienteController;
import static application.MainApp.getConnection;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    // Criar Cliente
    public void criarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nomecompleto, telefone, ddi, ddd, numero, emailcliente, endereco, logradouro, complemento, " +
                     "cep, tipopessoa, cpf, datanascim, contato, cnpj, ie, ddi2, ddd2, numero2, telefone2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getDdi());
            stmt.setString(4, cliente.getDdd());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getEmailCliente());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getLogradouro());
            stmt.setString(9, cliente.getComplemento());
            stmt.setString(10, cliente.getCep());
            stmt.setInt(11, cliente.getTipoPessoa());
            stmt.setString(12, cliente.getCPF());
            stmt.setDate(13, cliente.getDataNascim() != null ? new Date(cliente.getDataNascim().getTime()) : null);
            stmt.setString(14, cliente.getContato());
            stmt.setString(15, cliente.getCNPJ());
            stmt.setString(16, cliente.getIE());
            stmt.setString(17, cliente.getDdi2());
            stmt.setString(18, cliente.getDdd2());
            stmt.setString(19, cliente.getNumero2());
            stmt.setString(20, cliente.getTelefone2());
            stmt.executeUpdate();            
            System.out.println("Cliente criado com sucesso!");            

        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
    }

    // Buscar Cliente
    public Cliente buscarCliente(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE iDClientePK = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getInt("iDClientePK"),
                        rs.getString("nomecompleto"),
                        rs.getString("telefone"),
                        rs.getString("ddi"),
                        rs.getString("ddd"),
                        rs.getString("numero"),
                        rs.getString("emailcliente"),
                        rs.getString("endereco"),
                        rs.getString("logradouro"),
                        rs.getString("complemento"),
                        rs.getString("cep"),
                        rs.getInt("tipopessoa"),
                        rs.getString("cpf"),
                        rs.getDate("datanascim"),
                        rs.getString("contato"),                            
                        rs.getString("cnpj"),
                        rs.getString("ie"),
                        rs.getString("ddi2"),
                        rs.getString("ddd2"),
                        rs.getString("numero2"),
                        rs.getString("telefone2")
                    );                    

                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return cliente;
    }
    
     // Buscar Cliente
    public Cliente buscarClienteCPF(String idCPF) {
    Cliente cliente = null;
    String sql = "SELECT * FROM cliente WHERE CPF = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, idCPF);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("iDClientePK"),
                    rs.getString("nomecompleto"),
                    rs.getString("telefone"),
                    rs.getString("ddi"),
                    rs.getString("ddd"),
                    rs.getString("numero"),
                    rs.getString("emailcliente"),
                    rs.getString("endereco"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("cep"),
                    rs.getInt("tipopessoa"),
                    rs.getString("cpf"),
                    rs.getDate("datanascim"),
                    rs.getString("contato"),
                    rs.getString("cnpj"),
                    rs.getString("ie"),
                    rs.getString("ddi2"),
                    rs.getString("ddd2"),
                    rs.getString("numero2"),
                    rs.getString("telefone2")
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar cliente: " + e.getMessage());
    }
    return cliente;
}


    // Listar Clientes
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente Order BY nomecompleto";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("iDClientePK"),
                    rs.getString("nomecompleto"),
                    rs.getString("telefone"),
                    rs.getString("ddi"),
                    rs.getString("ddd"),
                    rs.getString("numero"),
                    rs.getString("emailcliente"),
                    rs.getString("endereco"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("cep"),
                    rs.getInt("tipopessoa"),
                    rs.getString("cpf"),
                    rs.getDate("datanascim"),
                    rs.getString("contato"),                            
                    rs.getString("cnpj"),
                    rs.getString("ie"),
                    rs.getString("ddi2"),
                    rs.getString("ddd2"),
                    rs.getString("numero2"),
                    rs.getString("telefone2")
                );
                clientes.add(cliente);

            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Atualizar Cliente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nomecompleto = ?, telefone = ?, ddi = ?, ddd = ?, numero = ?, emailcliente = ?, endereco = ?, " +
                     "logradouro = ?, complemento = ?, cep = ?, tipopessoa = ?, cpf = ?, datanascim = ?, contato= ?, cnpj = ?, ie = ?, ddi2 = ?, " +
                     "ddd2 = ?, numero2 = ?, telefone2 = ? WHERE iDClientePK = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getDdi());
            stmt.setString(4, cliente.getDdd());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getEmailCliente());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getLogradouro());
            stmt.setString(9, cliente.getComplemento());
            stmt.setString(10, cliente.getCep());
            stmt.setInt(11, cliente.getTipoPessoa());
            stmt.setString(12, cliente.getCPF());
            stmt.setDate(13, cliente.getDataNascim() != null ? new Date(cliente.getDataNascim().getTime()) : null);
            stmt.setString(14, cliente.getContato());            
            stmt.setString(15, cliente.getCNPJ());
            stmt.setString(16, cliente.getIE());
            stmt.setString(17, cliente.getDdi2());
            stmt.setString(18, cliente.getDdd2());
            stmt.setString(19, cliente.getNumero2());
            stmt.setString(20, cliente.getTelefone2());
            stmt.setInt(21, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Deletar Cliente
    public void deletarCliente(int idCliente) {
        String sql = "DELETE FROM cliente WHERE iDClientePK = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }
    
    
    public List<Cliente> pesquisarClientes(String valorPesquisa) {
    List<Cliente> clientesEncontrados = new ArrayList<>();
    String sql = "SELECT * FROM Cliente WHERE nomeCompleto LIKE ? OR telefone LIKE ? OR emailCliente LIKE ? OR endereco LIKE ? OR CPF LIKE ? OR CNPJ LIKE ?";

    try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        String pesquisa = "%" + valorPesquisa + "%";
        
        // Define os parâmetros da consulta
        for (int i = 1; i <= 6; i++) {
            pstmt.setString(i, pesquisa);
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente(
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
            clientesEncontrados.add(cliente);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return clientesEncontrados;
}

    
    
}
