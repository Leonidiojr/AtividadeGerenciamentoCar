package controller;

import static application.MainApp.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Oficina;

public class OficinaController {

    // Método para criar um novo registro na tabela Oficina
    public void criarOficina(Oficina oficina) {
        String sql = "INSERT INTO Oficina (CNPJ, razaoSocial, IE, logradouro, complemento, CEP, email, ddi, ddd, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oficina.getCNPJ());
            stmt.setString(2, oficina.getRazaoSocial());
            stmt.setString(3, oficina.getIE());
            stmt.setString(4, oficina.getLogradouro());
            stmt.setString(5, oficina.getComplemento());
            stmt.setString(6, oficina.getCEP());
            stmt.setString(7, oficina.getEmail());
            stmt.setString(8, oficina.getDdi());
            stmt.setString(9, oficina.getDdd());
            stmt.setString(10, oficina.getNumero());
            stmt.executeUpdate();
            System.out.println("Oficina criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar oficina: " + e.getMessage());
        }
    }

    // Método para buscar um registro na tabela Oficina pelo CNPJ
    public Oficina buscarOficina(String CNPJ) {
        Oficina oficina = null;
        String sql = "SELECT * FROM Oficina WHERE CNPJ = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, CNPJ);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oficina = new Oficina(
                        rs.getString("CNPJ"),
                        rs.getString("razaoSocial"),
                        rs.getString("IE"),
                        rs.getString("logradouro"),
                        rs.getString("complemento"),
                        rs.getString("CEP"),
                        rs.getString("email"),
                        rs.getString("ddi"),
                        rs.getString("ddd"),
                        rs.getString("numero")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar oficina: " + e.getMessage());
        }
        return oficina;
    }

    // Método para listar todos os registros da tabela Oficina
    public List<Oficina> listarOficinas() {
        List<Oficina> oficinas = new ArrayList<>();
        String sql = "SELECT * FROM Oficina";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Oficina oficina = new Oficina(
                    rs.getString("CNPJ"),
                    rs.getString("razaoSocial"),
                    rs.getString("IE"),
                    rs.getString("logradouro"),
                    rs.getString("complemento"),
                    rs.getString("CEP"),
                    rs.getString("email"),
                    rs.getString("ddi"),
                    rs.getString("ddd"),
                    rs.getString("numero")
                );
                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar oficinas: " + e.getMessage());
        }
        return oficinas;
    }

    // Método para atualizar um registro na tabela Oficina
    public void atualizarOficina(Oficina oficina) {
        String sql = "UPDATE Oficina SET razaoSocial = ?, IE = ?, logradouro = ?, complemento = ?, CEP = ?, email = ?, ddi = ?, ddd = ?, numero = ? WHERE CNPJ = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, oficina.getRazaoSocial());
            stmt.setString(2, oficina.getIE());
            stmt.setString(3, oficina.getLogradouro());
            stmt.setString(4, oficina.getComplemento());
            stmt.setString(5, oficina.getCEP());
            stmt.setString(6, oficina.getEmail());
            stmt.setString(7, oficina.getDdi());
            stmt.setString(8, oficina.getDdd());
            stmt.setString(9, oficina.getNumero());
            stmt.setString(10, oficina.getCNPJ());
            stmt.executeUpdate();
            System.out.println("Oficina atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar oficina: " + e.getMessage());
        }
    }

    // Método para deletar um registro na tabela Oficina pelo CNPJ
    public void deletarOficina(String CNPJ) {
        String sql = "DELETE FROM Oficina WHERE CNPJ = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, CNPJ);
            stmt.executeUpdate();
            System.out.println("Oficina deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar oficina: " + e.getMessage());
        }
    }


    
}

