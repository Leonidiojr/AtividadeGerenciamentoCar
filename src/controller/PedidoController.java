package controller;

import static application.MainApp.getConnection;
import application.Constants;
import model.Pedido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    public void criarPedido(Pedido pedido) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO pedido (id_produto, quantidade, valor_unitario) VALUES (?, ?, ?)";
            utils.Utils.DialogBoxes.MessageBox(sql, "");                                
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pedido.getIdProduto());
                stmt.setInt(2, pedido.getQuantidadeProduto());
                stmt.setBigDecimal(3, pedido.getValorUnitarioProduto());
                stmt.executeUpdate();
                System.out.println("Pedido criado com sucesso!");                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Pedido buscarPedido(int idPedido) {
        Pedido pedido = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pedido WHERE id_pedido = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPedido);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        pedido = new Pedido(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_produto"),
                            rs.getInt("quantidade"),
                            rs.getBigDecimal("valor_unitario")
                        );
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pedido";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pedido pedido = new Pedido(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_produto"),
                            rs.getInt("quantidade"),
                            rs.getBigDecimal("valor_unitario")
                        );
                        pedidos.add(pedido);
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pedidos;
    }

    public void atualizarPedido(Pedido pedido) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE pedido SET id_produto = ?, quantidade = ?, valor_unitario = ? WHERE id_pedido = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pedido.getIdProduto());
                stmt.setInt(2, pedido.getQuantidadeProduto());
                stmt.setBigDecimal(3, pedido.getValorUnitarioProduto());
                stmt.setInt(4, pedido.getIdPedido());
                stmt.executeUpdate();
                System.out.println("Pedido atualizado com sucesso!");                
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarPedido(int idPedido) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM pedido WHERE id_pedido = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPedido);
                stmt.executeUpdate();
                System.out.println("Pedido deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
