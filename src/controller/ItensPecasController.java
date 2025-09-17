package controller;

import static application.MainApp.getConnection;
import model.ItensPecas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensPecasController {

    // Método para criar um novo registro na tabela ItensPecas
    public void criarItensPecas(ItensPecas itensPecas) {
        String sql = "INSERT INTO ItensPecas (qntd, precoUnit, precoTotal, descricao, codFabricante) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itensPecas.getQntd());
            stmt.setDouble(2, itensPecas.getPrecoUnit());
            stmt.setDouble(3, itensPecas.getPrecoTotal());
            stmt.setString(4, itensPecas.getDescricao());
            stmt.setString(5, itensPecas.getCodFabricante());
            stmt.executeUpdate();
            System.out.println("Item de Peça criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar item de peça: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela ItensPecas pelo ID
    public ItensPecas buscarItensPecas(int idItensPK) {
        ItensPecas itensPecas = null;
        String sql = "SELECT * FROM ItensPecas WHERE idItensPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idItensPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    itensPecas = new ItensPecas(
                        rs.getInt("idItensPK"),
                        rs.getInt("qntd"),
                        rs.getDouble("precoUnit"),
                        rs.getDouble("precoTotal"),
                        rs.getString("descricao"),  // Adicionando a descrição
                        rs.getString("codFabricante")  // Adicionando o código do fabricante
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar item de peça: " + e.getMessage());
        }
        return itensPecas;
    }

    // Método para listar todos os registros da tabela ItensPecas
    public List<ItensPecas> listarItensPecas() {
        List<ItensPecas> itensPecasList = new ArrayList<>();
        String sql = "SELECT * FROM ItensPecas";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItensPecas itensPecas = new ItensPecas(
                    rs.getInt("idItensPK"),
                    rs.getInt("qntd"),
                    rs.getDouble("precoUnit"),
                    rs.getDouble("precoTotal"),
                    rs.getString("descricao"),  // Adicionando a descrição
                    rs.getString("codFabricante")  // Adicionando o código do fabricante
                );
                itensPecasList.add(itensPecas);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens de peças: " + e.getMessage());
        }
        return itensPecasList;
    }

    // Método para atualizar um registro na tabela ItensPecas
    public void atualizarItensPecas(ItensPecas itensPecas) {
        String sql = "UPDATE ItensPecas SET qntd = ?, precoUnit = ?, precoTotal = ?, descricao = ?, codFabricante = ? WHERE idItensPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itensPecas.getQntd());
            stmt.setDouble(2, itensPecas.getPrecoUnit());
            stmt.setDouble(3, itensPecas.getPrecoTotal());
            stmt.setString(4, itensPecas.getDescricao());
            stmt.setString(5, itensPecas.getCodFabricante());
            stmt.setInt(6, itensPecas.getIdItensPK());
            stmt.executeUpdate();
            System.out.println("Item de Peça atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item de peça: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela ItensPecas pelo ID
    public void deletarItensPecas(int idItensPK) {
        String sql = "DELETE FROM ItensPecas WHERE idItensPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idItensPK);
            stmt.executeUpdate();
            System.out.println("Item de Peça deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar item de peça: " + e.getMessage());
        }
    }

    // Método para buscar itens de peças por ID da ordem de serviço
    // Método para buscar itens de peças por ID da ordem de serviço
    public List<ItensPecas> buscarItensPecasPorOrdemServico(int ordemServicoId) {
        List<ItensPecas> itensPecas = new ArrayList<>();
        String sql = "SELECT i.iDItensPK, i.descricao, i.codFabricante, i.qntd, i.precoUnit, i.precoTotal " +
                     "FROM ItensPecas i " +
                     "WHERE i.idOS = ?"; // Corrigido para i.idOS

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ordemServicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItensPecas itemPeca = new ItensPecas(
                        rs.getInt("iDItensPK"),
                        rs.getInt("qntd"),
                        rs.getDouble("precoUnit"),
                        rs.getDouble("precoTotal"),
                        rs.getString("descricao"),  // Adicionando a descrição
                        rs.getString("codFabricante")  // Adicionando o código do fabricante
                    );
                    itensPecas.add(itemPeca);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Loga a exceção para depuração
        }
        return itensPecas;
    }
}
