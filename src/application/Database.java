package application;

import static application.Constants.DB_URL;
import static application.Constants.PASSWORD;
import static application.Constants.USER;
import static application.MainApp.getConnection;
import application.InsertRandomData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Funcionario;
import model.Cliente;
import javax.swing.JFrame;
import view.frmLoginDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
                
    public static boolean verificarPostgreSQL() {
        String url = Constants.URL;
        String user = Constants.USER;
        String password = Constants.PASSWORD;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("PostgreSQL está instalado e conectado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("PostgreSQL não está instalado ou não conseguiu se conectar.");
            JFrame frmLogin = new frmLoginDB();            
            frmLogin.setVisible(true);
            e.printStackTrace();
        }
        
        return false;
        }
    
    
    // Métodos para verificar conexão com o banco de dados
    public static boolean connectedDatabase() throws SQLException {
        if (verificarPostgreSQL()) {
            try (Connection conn = getConnection()) {
                if (conn != null) {
                    System.out.println("Detectada existencia da instalacao do banco de dados PostgreSQL" + Constants.DB_NAME + ".");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao detectar instalacao do banco de dados: " + e.getMessage());
                return false;
            }
            return false;
        } else {
            System.out.println("Erro ao conectar ao banco de dados: PostgreSQL não está instalado ou não conseguiu se conectar.");
            return false;
        }
    }

    // Método para verificar se estamos conectados ao banco de dados
    public static boolean isConnected() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conectado ao banco de dados com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }
    
    
        // Método para verificar se uma tabela existe no banco de dados
    public static boolean existeTabela(String tabela) {
        boolean existe = false;

        try (Connection conn = getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet rs = meta.getTables(null, null, tabela.toUpperCase(), null)) {
                if (rs.next()) {
                    System.out.println("A tabela " + tabela + " existe no banco de dados.");
                    existe = true;
                } else {
                    System.out.println("A tabela " + tabela + " não existe no banco de dados.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se a tabela existe: " + e.getMessage());
        }
        return existe;
    }
     
    
        // Método para verificar se um tipo ENUM existe no banco de dados
    public static boolean existeEnum(String enumName) {
        boolean existe = false;
        String sql = "SELECT 1 FROM pg_type WHERE typname = ? AND typtype = 'e'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enumName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("O tipo ENUM " + enumName + " existe no banco de dados.");
                    existe = true;
                } else {
                    System.out.println("O tipo ENUM " + enumName + " não existe no banco de dados.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o tipo ENUM existe: " + e.getMessage());
        }
        return existe;
    }
    
    
    public static boolean checkDatabase() {
        
        try (Connection conn = getConnection()) {
            String sql = "SELECT datname FROM pg_database WHERE datname = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, Constants.DB_NAME);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("O banco de dados da aplicacao existe: " + rs.getString("datname"));
                        return true;
                    } else {
                        System.out.println("O banco de dados da aplicacao não existe.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar existencia do banco de dados da aplicacao: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean prepareEnvironment() {
    try {
        if (!checkDatabase()) {
            if (createDatabase() && createTables()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true; // Caso o banco de dados já exista, o ambiente está pronto
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}     
    
    private static boolean createDatabase() throws SQLException {
                    
        try (Connection conn = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE " + Constants.DB_NAME;
            stmt.executeUpdate(sql);
            System.out.println("Banco de dados " + Constants.DB_NAME + " criado com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
            if (checkDatabase()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    
    
    public static boolean deleteBanco() {
        String dbName = Constants.DB_NAME;

        try (Connection conn = getConnection()) {
            String sqlCheck = "SELECT datname FROM pg_database WHERE datname = ?";
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setString(1, dbName);
                try (ResultSet rs = stmtCheck.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("O banco de dados da aplicacao existe: " + rs.getString("datname"));

                        // O banco de dados existe, então vamos deletá-lo
                        String sqlDelete = "DROP DATABASE " + dbName;
                        try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                            stmtDelete.executeUpdate();
                            System.out.println("Banco de dados deletado com sucesso: " + dbName);
                            return true;
                        } catch (SQLException e) {
                            System.err.println("Erro ao deletar o banco de dados da aplicacao: " + e.getMessage());
                            return false;
                        }
                    } else {
                        System.out.println("O banco de dados da aplicacao não existe.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar existencia do banco de dados da aplicacao: " + e.getMessage());
            return false;
        }
    }


public static boolean createTables() {
                
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Iniciando criação das tabelas auxiliares...");

            // Tabela ddi
            if(!existeTabela("ddi")){               
                String createTableDDI = "CREATE TABLE ddi (" +
                    "id SERIAL PRIMARY KEY," +  
                    "code VARCHAR(5) NOT NULL," + 
                    "country VARCHAR(100) NOT NULL," + 
                    "UNIQUE (code, country))";    
                stmt.executeUpdate(createTableDDI);            

            String createTableDDIInsert = "INSERT INTO ddi (code, country) VALUES " + 
                 "('+1', 'Estados Unidos/Canadá'), " +
                 "('+7', 'Rússia/Kazaquistão'), " +
                 "('+20', 'Egito'), " +
                 "('+27', 'África do Sul'), " +
                 "('+30', 'Grécia'), " +
                 "('+31', 'Países Baixos'), " +
                 "('+32', 'Bélgica'), " +
                 "('+33', 'França'), " +
                 "('+34', 'Espanha'), " +
                 "('+36', 'Hungria'), " +
                 "('+39', 'Itália'), " +
                 "('+40', 'Romênia'), " +
                 "('+41', 'Suíça'), " +
                 "('+43', 'Áustria'), " +
                 "('+44', 'Reino Unido'), " +
                 "('+45', 'Dinamarca'), " +
                 "('+46', 'Suécia'), " +
                 "('+47', 'Noruega'), " +
                 "('+48', 'Polônia'), " +
                 "('+49', 'Alemanha'), " +
                 "('+51', 'Peru'), " +
                 "('+52', 'México'), " +
                 "('+53', 'Cuba'), " +
                 "('+54', 'Argentina'), " +
                 "('+55', 'Brasil'), " +
                 "('+56', 'Chile'), " +
                 "('+57', 'Colômbia'), " +
                 "('+58', 'Venezuela'), " +
                 "('+60', 'Malásia'), " +
                 "('+61', 'Austrália'), " +
                 "('062', 'Indonésia'), " +
                 "('+63', 'Filipinas'), " +
                 "('+64', 'Nova Zelândia'), " +
                 "('+65', 'Cingapura'), " +
                 "('+66', 'Tailândia'), " +
                 "('+81', 'Japão'), " +
                 "('+82', 'Coreia do Sul'), " +
                 "('+84', 'Vietnã'), " +
                 "('+86', 'China'), " +
                 "('+90', 'Turquia'), " +
                 "('+91', 'Índia'), " +
                 "('+92', 'Paquistão'), " +
                 "('+93', 'Afeganistão'), " +
                 "('+94', 'Sri Lanka'), " +
                 "('+95', 'Mianmar'), " +
                 "('+98', 'Irã'), " +
                 "('+211', 'Sudão do Sul'), " +
                 "('+212', 'Marrocos'), " +
                 "('+213', 'Argélia'), " +
                 "('+216', 'Tunísia'), " +
                 "('+218', 'Líbia'), " +
                 "('+220', 'Gâmbia'), " +
                 "('+221', 'Senegal'), " +
                 "('+222', 'Mauritânia'), " +
                 "('+223', 'Mali'), " +
                 "('+224', 'Guiné'), " +
                 "('+225', 'Costa do Marfim'), " +
                 "('+226', 'Burkina Faso'), " +
                 "('+227', 'Níger'), " +
                 "('+228', 'Togo'), " +
                 "('+229', 'Benim'), " +
                 "('+230', 'Maurício'), " +
                 "('+231', 'Libéria'), " +
                 "('+232', 'Serra Leoa'), " +
                 "('+233', 'Gana'), " +
                 "('+234', 'Nigéria'), " +
                 "('+235', 'Chade'), " +
                 "('+236', 'República Centro-Africana'), " +
                 "('+237', 'Camarões'), " +
                 "('+238', 'Cabo Verde'), " +
                 "('+239', 'São Tomé e Príncipe'), " +
                 "('+240', 'Guiné Equatorial'), " +
                 "('+241', 'Gabão'), " +
                 "('+242', 'República do Congo'), " +
                 "('+243', 'República Democrática do Congo'), " +
                 "('+244', 'Angola'), " +
                 "('+245', 'Guiné-Bissau'), " +
                 "('+246', 'Diego Garcia'), " +
                 "('+247', 'Ilhas Ascensão'), " +
                 "('+248', 'Seychelles'), " +
                 "('+249', 'Sudão'), " +
                 "('+250', 'Ruanda'), " +
                 "('+251', 'Etiópia'), " +
                 "('+252', 'Somália'), " +
                 "('+253', 'Djibouti'), " +
                 "('+254', 'Quênia'), " +
                 "('+255', 'Tanzânia'), " +
                 "('+256', 'Uganda'), " +
                 "('+257', 'Burundi'), " +
                 "('+258', 'Moçambique'), " +
                 "('+260', 'Zâmbia'), " +
                 "('+261', 'Madagáscar'), " +
                 "('+262', 'Reunião/Mayotte'), " +
                 "('+263', 'Zimbábue'), " +
                 "('+264', 'Namíbia'), " +
                 "('+265', 'Malawi'), " +
                 "('+266', 'Lesoto'), " +
                 "('+267', 'Botsuana'), " +
                 "('+268', 'Essuatíni'), " +
                 "('+269', 'Comores'), " +
                 "('+290', 'Santa Helena'), " +
                 "('+291', 'Eritreia'), " +
                 "('+297', 'Aruba'), " +
                 "('+298', 'Ilhas Faroe'), " +
                 "('+299', 'Groenlândia'), " +
                 "('+350', 'Gibraltar'), " +
                 "('+351', 'Portugal'), " +
                 "('+352', 'Luxemburgo'), " +
                 "('+353', 'Irlanda'), " +
                 "('+354', 'Islândia'), " +
                 "('+355', 'Albânia'), " +
                 "('+356', 'Malta'), " +
                 "('+357', 'Chipre'), " +
                 "('+358', 'Finlândia'), " +
                 "('+359', 'Bulgária'), " +
                 "('+370', 'Lituânia'), " +
                 "('+371', 'Letônia'), " +
                 "('+372', 'Estônia'), " +
                 "('+373', 'Moldávia'), " +
                 "('+374', 'Armênia'), " +
                 "('+375', 'Bielorrússia'), " +
                 "('+376', 'Andorra'), " +
                 "('+377', 'Mônaco'), " +
                 "('+378', 'San Marino'), " +
                 "('+379', 'Cidade do Vaticano'), " +
                 "('+380', 'Ucrânia'), " +
                 "('+381', 'Sérvia'), " +
                 "('+382', 'Montenegro'), " +
                 "('+383', 'Kosovo'), " +
                 "('+385', 'Croácia'), " +
                 "('+386', 'Eslovênia'), " +
                 "('+387', 'Bósnia e Herzegovina'), " +
                 "('+389', 'Macedônia do Norte'), " +
                 "('+420', 'República Tcheca'), " +
                 "('+421', 'Eslováquia'), " +
                 "('+423', 'Liechtenstein');";
             stmt.executeUpdate(createTableDDIInsert);  
            }
        
            if(!existeTabela("ddd")){               
                String createTableDDD = "CREATE TABLE ddd ("   +
                    "id SERIAL PRIMARY KEY, " + 
                    "ddd VARCHAR(5) NOT NULL," + 
                    "city VARCHAR(100) NOT NULL, " + 
                    "state VARCHAR(100) NOT NULL,  " + 
                    "country VARCHAR(10) NOT NULL, " + 
                    "UNIQUE (ddd, city))" ;
                stmt.executeUpdate(createTableDDD);                            
            
                String createTableDDDInsert = "INSERT INTO ddd (ddd, city, state, country) VALUES "
                    + // Goiás (DDD 62)
                    "('062', 'Senador Canedo', 'Goiás', '+55'), "
                    + "('064', 'Crixás', 'Goiás', '+55'), "
                    + // Rio de Janeiro (DDD 21, 22, 24)
                    "('021', 'Rio de Janeiro', 'Rio de Janeiro', '+55'), "
                    + "('022', 'Niterói', 'Rio de Janeiro', '+55'), "
                    + "('024', 'Campos dos Goytacazes', 'Rio de Janeiro', '+55'), "
                    + // Minas Gerais (DDD 31, 32, 33, 34, 35, 37, 38)
                    "('031', 'Belo Horizonte', 'Minas Gerais', '+55'), "
                    + "('032', 'Juiz de Fora', 'Minas Gerais', '+55'), "
                    + "('033', 'Governador Valadares', 'Minas Gerais', '+55'), "
                    + "('034', 'Uberlândia', 'Minas Gerais', '+55'), "
                    + "('035', 'Poços de Caldas', 'Minas Gerais', '+55'), "
                    + "('037', 'Divinópolis', 'Minas Gerais', '+55'), "
                    + "('038', 'Montes Claros', 'Minas Gerais', '+55'), "
                    + // Bahia (DDD 71, 73, 74, 75, 77)
                    "('071', 'Salvador', 'Bahia', '+55'), "
                    + "('073', 'Itabuna', 'Bahia', '+55'), "
                    + "('074', 'Feira de Santana', 'Bahia', '+55'), "
                    + "('075', 'Camaçari', 'Bahia', '+55'), "
                    + "('077', 'Vitória da Conquista', 'Bahia', '+55'), "
                    + // Pernambuco (DDD 81, 87)
                    "('081', 'Recife', 'Pernambuco', '+55'), "
                    + "('087', 'Caruaru', 'Pernambuco', '+55'), "
                    + // Ceará (DDD 85, 88)
                    "('085', 'Fortaleza', 'Ceará', '+55'), "
                    + "('088', 'Juazeiro do Norte', 'Ceará', '+55'), "
                    + // Rio Grande do Sul (DDD 51, 53, 54, 55)
                    "('051', 'Porto Alegre', 'Rio Grande do Sul', '+55'), "
                    + "('053', 'Caxias do Sul', 'Rio Grande do Sul', '+55'), "
                    + "('054', 'Passo Fundo', 'Rio Grande do Sul', '+55'), "
                    + "('055', 'Santa Maria', 'Rio Grande do Sul', '+55'), "
                    + // Paraná (DDD 41, 42, 43, 44)
                    "('041', 'Curitiba', 'Paraná', '+55'), "
                    + "('042', 'Ponta Grossa', 'Paraná', '+55'), "
                    + "('043', 'Londrina', 'Paraná', '+55'), "
                    + "('044', 'Maringá', 'Paraná', '+55'), "
                    + // Santa Catarina (DDD 48, 49)
                    "('048', 'Florianópolis', 'Santa Catarina', '+55'), "
                    + "('049', 'Chapecó', 'Santa Catarina', '+55');";

                stmt.executeUpdate(createTableDDDInsert);
            }
                                                   
            System.out.println("Iniciando criação das tabelas da aplicacao...");

            
            // Tabela Cliente
            if(!existeTabela("Cliente")){               
                String createClienteTable = "CREATE TABLE Cliente (" +
                    "iDClientePK SERIAL PRIMARY KEY, " +
                    "ddi VARCHAR(3) NOT NULL, " +
                    "ddd VARCHAR(3) NOT NULL, " +
                    "numero VARCHAR(15) NOT NULL, " +
                    "telefone VARCHAR(20) NOT NULL, " +                  
                    "nomeCompleto VARCHAR(50) NOT NULL, " +
                    "emailcliente VARCHAR(30), " +
                    "logradouro VARCHAR(100), " +
                    "complemento VARCHAR(50), " +
                    "CEP VARCHAR(10), " +
                    "endereco VARCHAR(100), " +                  
                    "CPF VARCHAR(14), " +
                    "dataNascim DATE, " +
                    "CNPJ VARCHAR(18), " +
                    "contato VARCHAR(100), " +                  
                    "IE VARCHAR(15), " +
                    "tipoPessoa INT, " +
                    "ddi2 VARCHAR(3), " +
                    "ddd2 VARCHAR(3), " +
                    "numero2 VARCHAR(15), " +
                    "telefone2 VARCHAR(20), " +                                    
                    "UNIQUE (ddi, ddd, numero)" +
                    ")";
                stmt.executeUpdate(createClienteTable);
                System.out.println("Tabela de clientes criada com sucesso...");
            }
                                    
            // Tabela Oficina
            if(!existeTabela("Oficina")){ 
                String createOficinaTable = "CREATE TABLE Oficina (" +
                    "CNPJ VARCHAR(18) PRIMARY KEY, " +
                    "razaoSocial VARCHAR(50) NOT NULL, " +
                    "IE VARCHAR(18) NOT NULL, " +
                    "logradouro VARCHAR(100), " +
                    "complemento VARCHAR(50), " +
                    "CEP VARCHAR(10), " +
                    "email VARCHAR(100) UNIQUE, " +
                    "ddi VARCHAR(5), " +
                    "ddd VARCHAR(5), " +
                    "numero VARCHAR(15), " +
                    "UNIQUE (ddi, ddd, numero)" +
                    ")";
                stmt.executeUpdate(createOficinaTable);
                System.out.println("Tabela de Oficina criada com sucesso...");
            }

            // Tabela Modelo
            if(!existeTabela("Modelo")){ 
                String createModeloTable = "CREATE TABLE Modelo (" +
                    "iDModeloPK SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "idMarca INT NOT NULL, " +
                    "tipo VARCHAR(50) NOT NULL" +
                    ")";
                stmt.executeUpdate(createModeloTable);
                System.out.println("Tabela de Modelo criada com sucesso...");
            }

            // Tabela Marca
            if(!existeTabela("Marca")){ 
                String createMarcaTable = "CREATE TABLE Marca (" +
                    "iDMarcaPK SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL," +
                    "tipo VARCHAR(50) NOT NULL," +                    
                    "logo BYTEA, " +
                    "iDModeloFK INT " + //REFERENCES Modelo(iDModeloPK) ON DELETE CASCADE" +
                    ")";
                stmt.executeUpdate(createMarcaTable);
                System.out.println("Tabela de Marca criada com sucesso...");
            }    
            
            // Tabela Veiculo
            if(!existeTabela("Veiculo")){ 
                String createVeiculoTable = "CREATE TABLE Veiculo (" +
                    "placaPK VARCHAR(10) PRIMARY KEY, " +
                    "marcaId INT NOT NULL, " +                    
                    "modeloId INT NOT NULL, " +                                                        
                    "anoFabricacao Int NOT NULL, " +
                    "anoModelo Int NOT NULL," +
                    "chassi VARCHAR(50) UNIQUE, " +
                    "km DECIMAL(10, 2) NOT NULL, " +
                    "nPatrimonio INT NULL " +                
                    ")";
                stmt.executeUpdate(createVeiculoTable);
                System.out.println("Tabela de Veiculo criada com sucesso...");
            }

            if (!existeTabela("Propriedade")) {
                String createPropriedadeTable = "CREATE TABLE Propriedade ("                        
                        + "iDClienteFK INT REFERENCES Cliente(iDClientePK) ON DELETE CASCADE, "
                        + "placaFK VARCHAR(10) REFERENCES Veiculo(placaPK) ON DELETE CASCADE, "
                        + "dataHoraCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                        //+ Adiciona a coluna dataHoraCadastro com valor automático
                        //"PRIMARY KEY (iDClienteFK, placaFK)"
                        + ")";
                stmt.executeUpdate(createPropriedadeTable);
                System.out.println("Tabela de Propriedade criada com sucesso...");
            }           

            // Tabela Acessorio
            if(!existeTabela("Acessorio")){ 
                String createAcessorioTable = "CREATE TABLE Acessorio (" +
                    "iDAcessorioPK SERIAL PRIMARY KEY, " +
                    "descricao VARCHAR(100) NOT NULL" +
                    ")";
                stmt.executeUpdate(createAcessorioTable);
                System.out.println("Tabela de Acessorio criada com sucesso...");
            }
                                    
            if (!existeEnum("statusEnum")){
                // Criação do tipo ENUM para status
                String createStatusEnum = "CREATE TYPE statusEnum AS ENUM ('ABERTA', 'ORCAMENTO', 'APROVADA', 'EXECUTADA', 'AGUARDANDO_PAGAMENTO', 'FINALIZADA')";
                stmt.executeUpdate(createStatusEnum);
            }    
            
            if(!existeTabela("Servico")){     
                String createServicoTable = "CREATE TABLE Servico ("
                        + "iDServicoPK SERIAL PRIMARY KEY, "
                        + "descricao VARCHAR(50) NOT NULL, "
                        + "qntd INT NOT NULL, "
                        + "precoUnit DECIMAL(10, 2) NOT NULL "
                        + ")";
                stmt.executeUpdate(createServicoTable);
                createServicoTable = "INSERT INTO Servico (descricao, qntd, precoUnit) VALUES ('Alinhamento', 1, 100.00)";
                stmt.executeUpdate(createServicoTable);
                System.out.println("Tabela de Servico criada com sucesso...");
            }    

            if(!existeTabela("Funcionario")){     
                String createFuncionarioTable = "CREATE TABLE Funcionario ("
                        + "CPF VARCHAR(14) PRIMARY KEY, "
                        + "nomeComp VARCHAR(50) NOT NULL, "
                        + "departamento VARCHAR(50) NOT NULL, "
                        + "iDitensServFK INT NOT NULL"
                        + ")";
                stmt.executeUpdate(createFuncionarioTable);
                System.out.println("Tabela de Funcionario criada com sucesso...");
            }  

 // Tabela Pecas
            if(!existeTabela("Pecas")){     
                String createPecasTable = "CREATE TABLE Pecas (" +
                    "iDPecasPK SERIAL PRIMARY KEY, " +
                    "descricao VARCHAR(100) NOT NULL, " +
                    "codFabricante VARCHAR(50) NOT NULL, " +
                    "qntdEstoque INT NOT NULL, " +
                    "precoUnit DECIMAL(10, 2) NOT NULL, " +
                    "iDItensFK INT NOT NULL " + //REFERENCES ItensPecas(iDItensPK) ON DELETE CASCADE" +
                    ")";
                stmt.executeUpdate(createPecasTable);
                System.out.println("Tabela de Pecas criada com sucesso...");
            }
                        
            if (!existeEnum("tipo_pagamento")){
                String createTipoPagamento = " CREATE TYPE tipo_pagamento AS ENUM ('Dinheiro', 'Cartão', 'Transferência', 'Boleto')";
                stmt.executeUpdate(createTipoPagamento);
            }
            
            if (!existeTabela("Pagamento")) {
                String createPagamentoTable = "CREATE TABLE Pagamento ("
                        + "idPagamento SERIAL PRIMARY KEY, "
                        + "dataPagamento DATE NOT NULL, "
                        + "numeroOrdemServico INT NOT NULL, "
                        + "valorPago DECIMAL(10, 2) NOT NULL, "
                        + "tipoPagamento tipo_pagamento NOT NULL, "
                        + "deslocamento DECIMAL(10, 2) NOT NULL, "
                        + // Campo para deslocamento
                        "servicoGuincho DECIMAL(10, 2) NOT NULL"
                        + // Campo para serviço de guincho
                        ")";
                stmt.executeUpdate(createPagamentoTable);
                System.out.println("Tabela de Pagamento criada com sucesso...");
            }

            
            if (!existeTabela("OS")) {
                String createOSTable = "CREATE TABLE OS ("
                        + "iD SERIAL PRIMARY KEY, "
                        + "dataOS TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " // Altera para TIMESTAMP e define o valor padrão como hora atual
                        + "placa VARCHAR(15) NOT NULL, "
                        + "valorFinal DECIMAL(10, 2) NOT NULL, "
                        + "valorPago DECIMAL(10, 2) NOT NULL, "
                        + "status statusEnum, "
                        + "cliente_id INT NOT NULL, "
                        + "funcionario_cpf VARCHAR(14) NOT NULL, "
                        + "FOREIGN KEY (cliente_id) REFERENCES Cliente(iDClientePK), "
                        + "FOREIGN KEY (placa) REFERENCES Veiculo(placaPK), "
                        + "FOREIGN KEY (funcionario_cpf) REFERENCES Funcionario(CPF)"
                        + ")";
                stmt.executeUpdate(createOSTable);
                System.out.println("Tabela de Ordem de Servico criada com sucesso...");
            }

            
            if (!existeTabela("ItensServicos")) {
                String createItensServicosTable = "CREATE TABLE ItensServicos ("
                        + "iDItensServPK SERIAL PRIMARY KEY, "
                        + "boxServico INT NOT NULL, "
                        + "dataInicio DATE NOT NULL, "
                        + "dataFinalizado DATE NOT NULL, "
                        + "qntd INT NOT NULL, "
                        + "precoUnit DECIMAL(10, 2) NOT NULL, "
                        + "iDServicoFK INT NOT NULL, "
                        + "cpfFuncionario VARCHAR(14) NOT NULL, "
                        + // Campo para registrar o funcionário responsável
                        "idOS INT NOT NULL, "
                        + // Chave estrangeira para OS
                        "FOREIGN KEY (cpfFuncionario) REFERENCES Funcionario(CPF), "
                        + // Chave estrangeira para Funcionario
                        "FOREIGN KEY (idOS) REFERENCES OS(iD) ON DELETE CASCADE"
                        + // Chave estrangeira para OS
                        ")";
                stmt.executeUpdate(createItensServicosTable);
                System.out.println("Tabela de Itens Servicos criada com sucesso...");
            }               

            if (!existeTabela("ItensPecas")) {
                String createItensPecasTable = "CREATE TABLE ItensPecas ("
                        + "iDItensPK SERIAL PRIMARY KEY, "
                        + "qntd INT NOT NULL, "
                        + "precoUnit DECIMAL(10, 2) NOT NULL, "
                        + "precoTotal DECIMAL(10, 2) NOT NULL, "
                        + "descricao VARCHAR(100) NOT NULL, " // Adicionando a descrição
                        + "codFabricante VARCHAR(50) NOT NULL, " // Adicionando o código do fabricante
                        + "idOS INT NOT NULL, "
                        + "FOREIGN KEY (idOS) REFERENCES OS(iD) ON DELETE CASCADE" // Chave estrangeira para OS
                        + ")";
                stmt.executeUpdate(createItensPecasTable);
                System.out.println("Tabela de Itens Pecas criada com sucesso...");
            }

            
           
       

            //Finalizando a insercao de dados
            System.out.println("Inserindo dados aleatorios de clientes...");
            InsertRandomData insertRandomData = new InsertRandomData();

            try {
                System.out.println("Inserindo dados aleatorios na Tabela de Cliente...");
                insertRandomData.insertExampleClientes(getConnection());            
            } catch (Exception e) {
                System.out.println("Erro ao inserir dados nas tabelas: " + e.getMessage());
            }
            try {
                System.out.println("Inserindo dados aleatorios na Tabela de Marcas...");
                insertRandomData.insertExamplosMarcasVeiculos(getConnection());                            
            } catch (Exception e) {
                System.out.println("Erro ao inserir dados nas tabelas: " + e.getMessage());
            }
            try {
                System.out.println("Inserindo dados aleatorios na Tabela de Modelos...");
                insertRandomData.insertExemplosModelosVeiculos(getConnection());                            
            } catch (Exception e) {
                System.out.println("Erro ao inserir dados nas tabelas: " + e.getMessage());
            }            
            
            System.out.println("Tabelas criadas com sucesso.");                        
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            return false;
        }
    }   

}


