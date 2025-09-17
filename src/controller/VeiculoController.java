package controller;

import model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static application.MainApp.getConnection;

public class VeiculoController {

    // Método para criar um novo registro na tabela Veiculo
    public void criarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO Veiculo (placaPK, marcaId, modeloId, anoFabricacao, anoModelo, chassi, km, nPatrimonio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlacaPK());
            stmt.setInt(2, veiculo.getMarcaId());
            stmt.setInt(3, veiculo.getModeloId());
            stmt.setInt(4, veiculo.getAnoFabricacao());
            stmt.setInt(5, veiculo.getAnoModelo());
            stmt.setString(6, veiculo.getChassi());
            stmt.setDouble(7, veiculo.getKm());
            stmt.setObject(8, veiculo.getNPatrimonio());
            stmt.executeUpdate();
            System.out.println("Veiculo criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar veiculo: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Veiculo pela placa
    public Veiculo buscarVeiculoPorPlaca(String placaPK) {
        Veiculo veiculo = null;
        String sql = "SELECT * FROM Veiculo WHERE placaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placaPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo(
                        rs.getString("placaPK"),
                        rs.getInt("marcaId"),
                        rs.getInt("modeloId"),
                        rs.getInt("anoFabricacao"),
                        rs.getInt("anoModelo"),
                        rs.getString("chassi"),
                        rs.getDouble("km"),
                        rs.getObject("nPatrimonio", Integer.class)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veiculo: " + e.getMessage());
        }
        return veiculo;
    }

    // Método para listar todos os registros da tabela Veiculo
    public List<Veiculo> listarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM Veiculo ORDER BY placaPK";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("placaPK"),
                    rs.getInt("marcaId"),
                    rs.getInt("modeloId"),
                    rs.getInt("anoFabricacao"),
                    rs.getInt("anoModelo"),
                    rs.getString("chassi"),
                    rs.getDouble("km"),
                    rs.getObject("nPatrimonio", Integer.class)
                );
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veiculos: " + e.getMessage());
        }
        return veiculos;
    }

    // Método para atualizar um registro na tabela Veiculo
    public void atualizarVeiculo(Veiculo veiculo) {
        String sql = "UPDATE Veiculo SET marcaId = ?, modeloId = ?, anoFabricacao = ?, anoModelo = ?, chassi = ?, km = ?, nPatrimonio = ? WHERE placaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculo.getMarcaId());
            stmt.setInt(2, veiculo.getModeloId());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setInt(4, veiculo.getAnoModelo());
            stmt.setString(5, veiculo.getChassi());
            stmt.setDouble(6, veiculo.getKm());
            stmt.setObject(7, veiculo.getNPatrimonio());
            stmt.setString(8, veiculo.getPlacaPK());
            stmt.executeUpdate();
            System.out.println("Veiculo atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veiculo: " + e.getMessage());
        }
    }


    // Método para deletar um registro na tabela Veiculo pela placa
    public void deletarVeiculo(String placaPK) {
        String sql = "DELETE FROM Veiculo WHERE placaPK = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placaPK);
            stmt.executeUpdate();
            System.out.println("Veiculo deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar veiculo: " + e.getMessage());
        }
    }

    // Método para pesquisar veículos com base em vários critérios
    public List<Veiculo> pesquisarVeiculos(String valorPesquisa) {
    
        List<Veiculo> veiculosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM Veiculo WHERE placaPK LIKE ? OR chassi LIKE ? ORDER BY placaPK";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";

            // Define os parâmetros da consulta
            for (int i = 1; i <= 2; i++) {
                pstmt.setString(i, pesquisa);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getString("placaPK"),
                        rs.getInt("marcaId"),
                        rs.getInt("modeloId"),
                        rs.getInt("anoFabricacao"),
                        rs.getInt("anoModelo"),
                        rs.getString("chassi"),
                        rs.getDouble("km"),
                        rs.getObject("nPatrimonio", Integer.class)
                );
                veiculosEncontrados.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculosEncontrados;
    }

    
}

