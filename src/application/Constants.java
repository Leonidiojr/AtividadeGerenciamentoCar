package application;

/**
 *
 * @author leoni
 */
public class Constants {    
    
    //Constantes para conexao com banco de dados
    public static final String HOST = "10.21.233.56"; // ou o IP do servidor de banco de dados
    public static final String PORT = "5432"; // porta padrão do PostgreSQL
    public static final String DB_NAME = "carsys"; // nome do banco de dados
    public static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/";    
    public static final String USER = "postgres";
    public static final String PASSWORD = "M1n3rv@pm";
    public static final String DB_URL = Constants.FULL_DB_URL;    
    public static final String FULL_DB_URL = URL +  "carsys";
    
    
    
    
}
