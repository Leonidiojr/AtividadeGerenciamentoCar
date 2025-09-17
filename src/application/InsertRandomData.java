package application;

import static application.Constants.DB_URL;
import static application.Constants.PASSWORD;
import static application.Constants.USER;
import static application.MainApp.getConnection;
import static utils.Utils.DialogBoxes.convertStringToDateTime;
import static utils.Utils.DialogBoxes.getCurrentDate;
import static utils.Utils.DialogBoxes.getCurrentDateTime;
import com.github.javafaker.Faker;
import model.OrdemServico;
import controller.enums.StatusOS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import controller.OrdemServicoController;

public class InsertRandomData {

    public static void main(String[] args) {

    try (Connection conn = getConnection()) {
            insertRandomClientes(conn, 1000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRandomClientes(Connection conn, int numRecords) {
        String sql = "INSERT INTO Cliente (ddi, ddd, numero, telefone, nomeCompleto, emailcliente, logradouro, complemento, CEP, endereco, CPF, dataNascim, CNPJ, contato, IE, tipoPessoa, ddi2, ddd2, numero2, telefone2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Faker faker = new Faker();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < numRecords; i++) {
                pstmt.setString(1, faker.number().digits(3));
                pstmt.setString(2, faker.number().digits(3));
                pstmt.setString(3, faker.number().digits(10));
                pstmt.setString(4, faker.phoneNumber().phoneNumber());
                pstmt.setString(5, faker.name().fullName());
                pstmt.setString(6, faker.internet().emailAddress());
                pstmt.setString(7, faker.address().streetAddress());
                pstmt.setString(8, faker.lorem().word());
                pstmt.setString(9, faker.address().zipCode());
                pstmt.setString(10, faker.address().fullAddress());
                pstmt.setString(11, faker.number().digits(11));
                pstmt.setString(12, new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()));
                pstmt.setString(13, faker.number().digits(14));
                pstmt.setString(14, faker.company().name());
                pstmt.setString(15, faker.number().digits(9));
                pstmt.setInt(16, faker.number().numberBetween(0, 2));
                pstmt.setString(17, faker.number().digits(3));
                pstmt.setString(18, faker.number().digits(3));
                pstmt.setString(19, faker.number().digits(10));
                pstmt.setString(20, faker.phoneNumber().phoneNumber());

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("100 registros inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registros na tabela Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
  
    public static void insertExampleClientes(Connection conn) {
        
        String sql = "INSERT INTO Cliente (ddi, ddd, numero, telefone, nomeCompleto, emailcliente, logradouro, complemento, CEP, endereco, CPF, dataNascim, CNPJ, contato, IE, tipoPessoa, ddi2, ddd2, numero2, telefone2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String[] nomes = {"João Silva", "Maria Oliveira", "Ana Souza", "Carlos Pereira", "Lucas Santos"};
        String[] emails = {"joao.silva@example.com", "maria.oliveira@example.com", "ana.souza@example.com", "carlos.pereira@example.com", "lucas.santos@example.com"};
        Date dataNasc = new Date(); // Data de nascimento padrão

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < 100; i++) {
                pstmt.setString(1, String.format("%03d", i + 1));
                pstmt.setString(2, String.format("%03d", (i + 1) * 2));
                pstmt.setString(3, String.format("%015d", i + 1));
                pstmt.setString(4, "Telefone" + (i + 1));
                pstmt.setString(5, nomes[i]);
                pstmt.setString(6, emails[i]);
                pstmt.setString(7, "Logradouro " + (i + 1));
                pstmt.setString(8, "Complemento " + (i + 1));
                pstmt.setString(9, "CEP" + (i + 1));
                pstmt.setString(10, "Endereco " + (i + 1));
                pstmt.setString(11, String.format("%014d", i + 1));
                pstmt.setDate(12, new java.sql.Date(dataNasc.getTime()));
                pstmt.setString(13, String.format("%018d", i + 1));
                pstmt.setString(14, "Contato " + (i + 1));
                pstmt.setString(15, String.format("%015d", i + 1));
                pstmt.setInt(16, (i % 2) + 1);
                pstmt.setString(17, String.format("%03d", (i + 1) * 3));
                pstmt.setString(18, String.format("%03d", (i + 1) * 4));
                pstmt.setString(19, String.format("%015d", (i + 1) * 5));
                pstmt.setString(20, "Telefone2 " + (i + 1));

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("5 registros inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registros na tabela Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }


     public static void insertExampleVeiculos(Connection conn) {
        String sql = "INSERT INTO Veiculo (placaPK, anoFabricacao, chassi, km, nPatrimonio, anoModelo) VALUES (?, ?, ?, ?, ?, ?)";

        // Dados de exemplo
        String[] placas = {"ABC1234", "DEF5678", "GHI9012", "JKL3456", "MNO7890"};
        String[] chassi = {"9BWZZZ377VT004251", "8ADZZZ377XT004252", "1HGCM82633A123456", "1HGCM82633A654321", "1HGCM82633A789012"};
        double[] kms = {10000.50, 20500.00, 15000.75, 30000.25, 25000.00};
        Integer[] patrimonios = {1234, 5678, 9012, 3456, 7890};
        String anoFabricacao = "2018"; // Data padrão de fabricação
        String anoModelo = "2019";     // Data padrão de modelo

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < 5; i++) {
                pstmt.setString(1, placas[i]);
                pstmt.setDate(2, java.sql.Date.valueOf(anoFabricacao));
                pstmt.setString(3, chassi[i]);
                pstmt.setDouble(4, kms[i]);
                pstmt.setInt(5, patrimonios[i]);
                pstmt.setDate(6, java.sql.Date.valueOf(anoModelo));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("5 registros inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registros na tabela Veiculo: " + e.getMessage());
            e.printStackTrace();
        }
    }
     
    public static void insertExamplosMarcasVeiculos(Connection conn) throws SQLException {
        
        String[][] marcas = {
            {"1", "CHEVROLET", "CARROS"},
            {"2", "VOLKSWAGEN", "CARROS"},
            {"3", "FIAT", "CARROS"},
            {"4", "MERCEDES-BENZ", "CARROS"},
            {"5", "CITROEN", "CARROS"},
            {"6", "CHANA", "CARROS"},
            {"7", "HONDA", "CARROS"},
            {"8", "SUBARU", "CARROS"},
            {"10", "FERRARI", "CARROS"},
            {"11", "BUGATTI", "CARROS"},
            {"12", "LAMBORGHINI", "CARROS"},
            {"13", "FORD", "CARROS"},
            {"14", "HYUNDAI", "CARROS"},
            {"15", "JAC", "CARROS"},
            {"16", "KIA", "CARROS"},
            {"17", "GURGEL", "CARROS"},
            {"18", "DODGE", "CARROS"},
            {"19", "CHRYSLER", "CARROS"},
            {"20", "BENTLEY", "CARROS"},
            {"21", "SSANGYONG", "CARROS"},
            {"22", "PEUGEOT", "CARROS"},
            {"23", "TOYOTA", "CARROS"},
            {"24", "RENAULT", "CARROS"},
            {"25", "ACURA", "CARROS"},
            {"26", "ADAMO", "CARROS"},
            {"27", "AGRALE", "CARROS"},
            {"28", "ALFA ROMEO", "CARROS"},
            {"29", "AMERICAR", "CARROS"},
            {"31", "ASTON MARTIN", "CARROS"},
            {"32", "AUDI", "CARROS"},
            {"34", "BEACH", "CARROS"},
            {"35", "BIANCO", "CARROS"},
            {"36", "BMW", "CARROS"},
            {"37", "BORGWARD", "CARROS"},
            {"38", "BRILLIANCE", "CARROS"},
            {"41", "BUICK", "CARROS"},
            {"42", "CBT", "CARROS"},
            {"43", "NISSAN", "CARROS"},
            {"44", "CHAMONIX", "CARROS"},
            {"46", "CHEDA", "CARROS"},
            {"47", "CHERY", "CARROS"},
            {"48", "CORD", "CARROS"},
            {"49", "COYOTE", "CARROS"},
            {"50", "CROSS LANDER", "CARROS"},
            {"51", "DAEWOO", "CARROS"},
            {"52", "DAIHATSU", "CARROS"},
            {"53", "VOLVO", "CARROS"},
            {"54", "DE SOTO", "CARROS"},
            {"55", "DETOMAZO", "CARROS"},
            {"56", "DELOREAN", "CARROS"},
            {"57", "DKW-VEMAGV", "CARROS"},
            {"59", "SUZUKI", "CARROS"},
            {"60", "EAGLE", "CARROS"},
            {"61", "EFFA", "CARROS"},
            {"63", "ENGESA", "CARROS"},
            {"64", "ENVEMO", "CARROS"},
            {"65", "FARUS", "CARROS"},
            {"66", "FERCAR", "CARROS"},
            {"68", "FNM", "CARROS"},
            {"69", "PONTIAC", "CARROS"},
            {"70", "PORSCHE", "CARROS"},
            {"72", "GEO", "CARROS"},
            {"74", "GRANCAR", "CARROS"},
            {"75", "GREAT WALL", "CARROS"},
            {"76", "HAFEI", "CARROS"},
            {"78", "HOFSTETTER", "CARROS"},
            {"79", "HUDSON", "CARROS"},
            {"80", "HUMMER", "CARROS"},
            {"82", "INFINITI", "CARROS"},
            {"83", "INTERNATIONAL", "CARROS"},
            {"86", "JAGUAR", "CARROS"},
            {"87", "JEEP", "CARROS"},
            {"88", "JINBEI", "CARROS"},
            {"89", "JPX", "CARROS"},
            {"90", "KAISER", "CARROS"},
            {"91", "KOENIGSEGG", "CARROS"},
            {"92", "LAUTOMOBILE", "CARROS"},
            {"93", "LAUTOCRAFT", "CARROS"},
            {"94", "LADAV", "CARROS"},
            {"95", "LANCI", "CARROS"},
            {"96", "LAND ROVER", "CARROS"},
            {"97", "LEXUS", "CARROS"},
            {"98", "LIFAN", "CARROS"},
            {"99", "LINCOLN", "CARROS"},
            {"100", "LOBINI", "CARROS"},
            {"101", "LOTUS", "CARROS"},
            {"102", "MAHINDRA", "CARROS"},
            {"104", "MASERATI", "CARROS"},
            {"106", "MATRA", "CARROS"},
            {"107", "MAYBACH", "CARROS"},
            {"108", "MAZDA", "CARROS"},
            {"109", "MENON", "CARROS"},
            {"110", "MERCURY", "CARROS"},
            {"111", "MITSUBISHI", "CARROS"},
            {"112", "MG", "CARROS"},
            {"113", "MINI", "CARROS"},
            {"114", "MIURA", "CARROS"},
            {"115", "MORRIS", "CARROS"},
            {"116", "MP LAFER", "CARROS"},
            {"117", "MPLM", "CARROS"},
            {"118", "NEWTRACK", "CARROS"},
            {"119", "NISSIN", "CARROS"},
            {"120", "OLDSMOBILE", "CARROS"},
            {"121", "PAG", "CARROS"},
            {"122", "PAGANI", "CARROS"},
            {"123", "PLYMOUTH", "CARROS"},
            {"124", "PUMA", "CARROS"},
            {"125", "RENO", "CARROS"},
            {"126", "REVA-I", "CARROS"},
            {"127", "ROLLS-ROYCE", "CARROS"},
            {"129", "ROMI", "CARROS"},
            {"130", "SEAT", "CARROS"},
            {"131", "UTILITARIOS AGRICOLAS", "CARROS"},
            {"132", "SHINERAY", "CARROS"},
            {"137", "SAAB", "CARROS"},
            {"139", "SHORT", "CARROS"},
            {"141", "SIMCA", "CARROS"},
            {"142", "SMART", "CARROS"},
            {"143", "SPYKER", "CARROS"},
            {"144", "STANDARD", "CARROS"},
            {"145", "STUDEBAKER", "CARROS"},
            {"146", "TAC", "CARROS"},
            {"147", "TANGER", "CARROS"},
            {"148", "TRIUMPH", "CARROS"},
            {"149", "TROLLER", "CARROS"},
            {"150", "UNIMOG", "CARROS"},
            {"154", "WIESMANN", "CARROS"},
            {"155", "CADILLAC", "CARROS"},
            {"156", "AM GEN", "CARROS"},
            {"157", "BUGGY", "CARROS"},
            {"158", "WILLYS OVERLAND", "CARROS"},
            {"161", "KASEA", "CARROS"},
            {"162", "SATURN", "CARROS"},
            {"163", "SWELL MINI", "CARROS"},
            {"175", "SKODA", "CARROS"},
            {"239", "KARMANN GHIA", "CARROS"},
            {"254", "KART", "CARROS"},
            {"258", "HANOMAG", "CARROS"},
            {"261", "OUTROS", "CARROS"},
            {"265", "HILLMAN", "CARROS"},
            {"288", "HRG", "CARROS"},
            {"295", "GAIOLA", "CARROS"},
            {"338", "TATA", "CARROS"},
            {"341", "DITALLY", "CARROS"},
            {"345", "RELY", "CARROS"},
            {"346", "MCLAREN", "CARROS"},
            {"534", "GEELY", "CARROS"},
        };

        String sql = "INSERT INTO Marca (idMarcaPK, nome, tipo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (String[] marca : marcas) {
                stmt.setInt(1, Integer.parseInt(marca[0]));
                stmt.setString(2, marca[1]);
                stmt.setString(3, marca[2]);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Registros inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
            throw e;  // Propaga a exceção para permitir que o chamador saiba sobre o erro
        }
    }


    public static void insertExemplosModelosVeiculos(Connection conn) throws SQLException {
    
        try {
            String filePath = "C:/Users/leoni/OneDrive/Documentos/NetBeansProjects/CarSys/src/modelos.csv";

            String sql = "INSERT INTO Modelo (iDModeloPK, nome, idMarca, tipo) VALUES (?, ?, ?, ?)";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath)); PreparedStatement stmt = conn.prepareStatement(sql)) {

                String line;
                boolean isHeader = false;

                while ((line = br.readLine()) != null) {
                    line = line.trim(); // Remove espaços extras da linha

                    /*
                if (isHeader) { 
                    if (line.equalsIgnoreCase("﻿idmodelopk;nome;idmarca;tipo;")) {
                        isHeader = false; // Cabeçalho detectado, pule para os dados
                        continue;
                    } else {
                        //throw new IOException("Cabeçalho inválido no arquivo: " + line);
                    }
                }*/
                    // Log para depuração
                    System.out.println("Lendo linha: " + line);

                    String[] modelo = line.split(";");
                    if (modelo.length == 4) {
                        try {
                            stmt.setInt(1, Integer.parseInt(modelo[0].trim()));
                            stmt.setString(2, modelo[1].trim());
                            stmt.setInt(3, Integer.parseInt(modelo[2].trim()));
                            stmt.setString(4, modelo[3].trim());
                            stmt.addBatch();
                        } catch (NumberFormatException e) {
                            System.err.println("Erro ao converter valores na linha: " + line + " - " + e.getMessage());
                        }
                    } else {
                        System.err.println("Linha inválida (esperado 4 campos): " + line);
                    }
                }

                // Executa o lote de inserções
                stmt.executeBatch();
                System.out.println("Registros inseridos com sucesso!");

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erro ao executar o SQL: " + e.getMessage());
                throw new SQLException("Erro ao executar o SQL", e); // Relança a exceção com mais contexto
            }            
        } catch (Exception e) {
        }
        
    }
    
        

}
