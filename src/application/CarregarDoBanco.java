package application;

import static application.MainApp.getConnection;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CarregarDoBanco {

    //Carregar dados de DDI para jComboBox
    public static void carregarDDIs(JComboBox<String> comboBoxDDI) {
        // Conexão com o banco de dados e carregamento dos DDIs
        try (Connection conn = getConnection()) { // Use seu método de conexão
            // Consulta SQL para obter os DDIs
            String query = "SELECT code, country FROM ddi ORDER BY code";
            try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

                // Adiciona cada resultado ao JComboBox
                while (resultSet.next()) {
                    String code = resultSet.getString("code");
                    String country = resultSet.getString("country");
                    comboBoxDDI.addItem(code);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar DDIs do banco de dados:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Carregar dados de DDD para jComboBox
    public static void carregarDDDs(JComboBox<String> comboBoxDDI, String codeCountry) {
        // Conexão com o banco de dados e carregamento dos DDDs
        try (Connection conn = getConnection()) { // Use seu método de conexão
            // Consulta SQL para obter os DDDs, usando o codeCountry como filtro
            String query = "SELECT distinct ddd, city, state, country FROM ddd WHERE country = ? ORDER BY ddd";

            try (PreparedStatement statement = conn.prepareStatement(query)) {
                // Substitui o parâmetro na consulta SQL com o valor de codeCountry
                statement.setString(1, codeCountry);

                // Executa a consulta e obtém o ResultSet
                try (ResultSet resultSet = statement.executeQuery()) {

                    // Adiciona cada resultado ao JComboBox
                    while (resultSet.next()) {
                        String ddd = resultSet.getString("ddd");
                        String city = resultSet.getString("city");
                        String state = resultSet.getString("state");
                        String country = resultSet.getString("country");

                        // Formata e adiciona o item ao JComboBox
                        comboBoxDDI.addItem(ddd);
                        //comboBoxDDI.addItem(ddd + " - " + city + ", " + state + " - " + country);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar DDDs do banco de dados:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Carregar dados de Marcas para jComboBox
    public static void carregarMarcas(JComboBox<String> comboBoxMarcas, boolean blnIncludeDesc) {
        // Conexão com o banco de dados e carregamento das Marcas
        try (Connection conn = getConnection()) { // Use seu método de conexão
            // Consulta SQL para obter as Marcas
            String query = "SELECT idMarcaPK, nome FROM Marca ORDER BY nome";

            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Adiciona cada resultado ao JComboBox
                while (resultSet.next()) {
                    int idMarcaPK = resultSet.getInt("idMarcaPK");
                    String nome = resultSet.getString("nome");

                    // Formata e adiciona o item ao JComboBox
                    if (blnIncludeDesc){
                        comboBoxMarcas.addItem(idMarcaPK + " - " + nome);
                    } else comboBoxMarcas.addItem(String.valueOf(idMarcaPK));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar Marcas do banco de dados:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    // Carregar dados de Modelos para jComboBox
    public static void carregarModelos(JComboBox<String> comboBoxModelos, boolean blnIncludeDesc) {
        // Conexão com o banco de dados e carregamento dos Modelos
        try (Connection conn = getConnection()) { // Use seu método de conexão
            // Consulta SQL para obter os Modelos
            String query = "SELECT idModeloPK, nome FROM Modelo ORDER BY nome";

            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Adiciona cada resultado ao JComboBox
                while (resultSet.next()) {
                    int idModeloPK = resultSet.getInt("idModeloPK");
                    String nome = resultSet.getString("nome");

                    // Formata e adiciona o item ao JComboBox
                    if (blnIncludeDesc) {
                         comboBoxModelos.addItem(idModeloPK + " - " + nome);
                    } else comboBoxModelos.addItem(String.valueOf(idModeloPK));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar Modelos do banco de dados:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void carregarClientes(JComboBox<String> comboBoxClientes, boolean blnIncludeDesc) {
    // Conexão com o banco de dados e carregamento dos Clientes
    try (Connection conn = getConnection()) { // Use seu método de conexão
        // Consulta SQL para obter os Clientes
        String query = "SELECT idClientePK, nomeCompleto, CPF FROM Cliente ORDER BY idClientePK";

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Adiciona cada resultado ao JComboBox
            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idClientePK");
                String nomeCompleto = resultSet.getString("nomeCompleto");
                String CPF = resultSet.getString("CPF");

                // Formata e adiciona o item ao JComboBox
                if (blnIncludeDesc) {
                    comboBoxClientes.addItem(idCliente + " - " + nomeCompleto + " - " + CPF);
                } else {
                    comboBoxClientes.addItem(String.valueOf(idCliente));
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
                "Erro ao carregar Clientes do banco de dados:\n" + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}



}
