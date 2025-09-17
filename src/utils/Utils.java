package utils;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe Utilitária
 * Autor: Leoni
 */
public class Utils {

    public static class DialogBoxes {

        /**
         * Método para exibir uma MessageBox com um input e uma mensagem de saudação
         *
         * @param strMessage Mensagem de input para o usuário
         * @param strTitle   Título da mensagem de saudação
         */
        public static void MessageBox(String strMessage, String strTitle) {
            if (strMessage != null) { // Verifica se a mensagem não é nula
                JOptionPane.showMessageDialog(null, strMessage, strTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        }    
        
        public static String InputBox(String strMessage, String strTitle) {
            String nome;
            StringBuilder mensagem = new StringBuilder();

            nome = JOptionPane.showInputDialog(strMessage);
            if (nome != null) { // Verifica se o usuário não cancelou a entrada
                mensagem.append(strTitle).append(" ").append(nome).append("!");
                // Exibe a mensagem de saudação
                //JOptionPane.showMessageDialog(null, mensagem.toString());
            }
            return nome; // Retorna o valor inserido pelo usuário
        
        }
         
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(dateFormatter);
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(timeFormatter);
    }
    
    public static LocalDateTime convertStringToDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }    
    }        
    
    
    // Função para validar e-mail
    public static boolean validarEmail(String email) {
        // Expressão regular para validar o formato do e-mail
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Verifica se o e-mail corresponde ao padrão
        return matcher.matches();
    }
 
        // Função para validar telefone no formato 0000-0000
    public static boolean validarTelefone(String telefone) {
    // Expressão regular para validar o formato do telefone
        String regex = "^[0-9]{5}-[0-9]{4}$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefone);

        // Verifica se o telefone corresponde ao padrão
        return matcher.matches();
    }
    
    //funcao para validar CPF
    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos (pontos, hífens)
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se o CPF não é uma sequência de números repetidos (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular e validar o primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma1 % 11);
        if (digito1 >= 10) digito1 = 0;

        // Calcular e validar o segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma2 % 11);
        if (digito2 >= 10) digito2 = 0;

        // Verificar se os dois dígitos verificadores são corretos
        return cpf.charAt(9) == (char) ('0' + digito1) && cpf.charAt(10) == (char) ('0' + digito2);
    }
    
    //funcao para validar CNPJ
    public static boolean validarCNPJ(String cnpj) {
        // Remover caracteres não numéricos (pontos, barras, hífens)
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verificar se o CNPJ não é uma sequência de números repetidos (ex: 11.111.111/1111-11)
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Calcular e validar o primeiro dígito verificador
        int soma1 = 0;
        int peso1[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma1 += (cnpj.charAt(i) - '0') * peso1[i];
        }
        int digito1 = 11 - (soma1 % 11);
        if (digito1 >= 10) digito1 = 0;

        // Calcular e validar o segundo dígito verificador
        int soma2 = 0;
        int peso2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma2 += (cnpj.charAt(i) - '0') * peso2[i];
        }
        int digito2 = 11 - (soma2 % 11);
        if (digito2 >= 10) digito2 = 0;

        // Verificar se os dois dígitos verificadores são corretos
        return cnpj.charAt(12) == (char) ('0' + digito1) && cnpj.charAt(13) == (char) ('0' + digito2);
    }
    
    //funcao para validar placa de veiculo
    public static boolean validarPlaca(String placa) {
        // Remover espaços em branco
        placa = placa.trim();

        // Verificar se a placa tem o formato válido (ex: ABC-1234 ou ABC1D23)
        // Verifica se segue o padrão de 3 letras + 1 separador + 4 números (ex: ABC-1234)
        // Ou 3 letras + 1 número + 1 letra + 2 números (ex: ABC1D23)
        String regex = "^[A-Z]{3}-[0-9]{4}$|^[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}$";
        
        return placa.matches(regex);
    }
     
     public static boolean validarChassi(String chassi) {
        // Remover espaços em branco
        chassi = chassi.trim();
        
        // Verificar se o número do chassi tem 17 caracteres
        if (chassi.length() != 17) {
            return false;
        }
        
        // Verificar se contém apenas caracteres válidos (A-Z, 0-9), excluindo I, O e Q
        String regex = "^[A-HJ-NPR-Z0-9]{17}$";
        
        // A expressão regular garante que:
        // - O chassi tenha 17 caracteres.
        // - Apenas letras de A-H, J-N, P-R, S-Z e números de 0-9 sejam permitidos.
        
        return chassi.matches(regex);
    }
     
    // Método para validar o número do RENAVAM
    public static boolean validarRenavam(String renavam) {
        // Remover espaços em branco
        renavam = renavam.trim();

        // Verificar se o RENAVAM tem 11 caracteres e se são todos numéricos
        if (renavam.length() != 11 || !renavam.matches("\\d{11}")) {
            return false;
        }

        return true;
    }
         

    public static boolean isValidDate(String dateStr) {
        // Define o formato da data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Desativa a leniência para garantir a validação rigorosa

        try {
            // Tenta analisar a string da data
            Date date = sdf.parse(dateStr);
            return true; // Data válida
        } catch (ParseException e) {
            // Exceção lançada se a data não for válida
            return false;
        }
  
    }
}
