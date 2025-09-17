package controller;

import static application.MainApp.getConnection;
import application.Constants;
import model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    public void criarFuncionario(Funcionario funcionario) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO Funcionario (CPF, nomeComp, departamento, iDitensServFK) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, funcionario.getCPF());
                stmt.setString(2, funcionario.getNomeComp());
                stmt.setString(3, funcionario.getDepartamento());
                stmt.setInt(4, funcionario.getiDitensServFK());
                stmt.executeUpdate();
                System.out.println("Funcionario criado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }        
    }

    public Funcionario buscarFuncionario(String cpf) {
        Funcionario funcionario = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Funcionario WHERE CPF = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        funcionario = new Funcionario(
                                rs.getString("CPF"),
                                rs.getString("nomeComp"),
                                rs.getString("departamento"),
                                rs.getInt("iDitensServFK")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Funcionario ORDER BY nomeComp";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Funcionario funcionario = new Funcionario(
                            rs.getString("CPF"),
                            rs.getString("nomeComp"),
                            rs.getString("departamento"),
                            rs.getInt("iDitensServFK")
                        );
                        funcionarios.add(funcionario);
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return funcionarios;
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Funcionario SET nomeComp = ?, departamento = ?, iDitensServFK = ? WHERE CPF = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, funcionario.getNomeComp());
                stmt.setString(2, funcionario.getDepartamento());
                stmt.setInt(3, funcionario.getiDitensServFK());
                stmt.setString(4, funcionario.getCPF());
                stmt.executeUpdate();
                System.out.println("Funcionario atualizado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarFuncionario(String cpf) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM Funcionario WHERE CPF = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                stmt.executeUpdate();
                System.out.println("Funcionario deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
    public List<Funcionario> pesquisarFuncionarios(String valorPesquisa) {
        List<Funcionario> funcionariosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario WHERE nomeComp LIKE ? OR CPF LIKE ? OR departamento LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String pesquisa = "%" + valorPesquisa + "%";
            
            // Define os parâmetros da consulta
            for (int i = 1; i <= 3; i++) {
                pstmt.setString(i, pesquisa);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getString("CPF"),                    
                        rs.getString("nomeComp"),                    
                        rs.getString("departamento"),
                        rs.getInt("iDitensServFK")
                );
                funcionariosEncontrados.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionariosEncontrados;
    }
}



