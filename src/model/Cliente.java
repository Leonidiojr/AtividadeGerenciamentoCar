package model;

//Classe Cliente

import static application.MainApp.ListaClientes;
import java.util.Date;
import java.util.List;
import controller.ClienteController;


public class Cliente {
    private int idCliente;
    private String nomeCompleto;
    private String telefone;
    private String ddi;
    private String ddd;
    private String numero;
    private String emailCliente;
    private String endereco;
    private String logradouro;
    private String complemento;
    private String cep;
    private int tipoPessoa;
    private String CPF;
    private Date dataNascim;
    private String CNPJ;
    private String contato;
    private String IE;
    private String ddi2;
    private String ddd2;
    private String numero2;
    private String telefone2;    
                   
    // Construtor

    public Cliente(int idCliente, String nomeCompleto, String telefone, String ddi, String ddd, String numero, String emailCliente, String endereco, String logradouro, String complemento, String cep, int tipoPessoa, String CPF, Date dataNascim, String contato, String CNPJ, String IE, String ddi2, String ddd2, String numero2, String telefone2) {
        this.idCliente = idCliente;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.emailCliente = emailCliente;
        this.endereco = endereco;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.cep = cep;
        this.tipoPessoa = tipoPessoa;
        this.CPF = CPF;
        this.dataNascim = dataNascim;
        this.contato = contato;
        this.CNPJ = CNPJ;
        this.IE = IE;
        this.ddi2 = ddi2;
        this.ddd2 = ddd2;
        this.numero2 = numero2;
        this.telefone2 = telefone2;
    }
   

    // Métodos getters e setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(int tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getDataNascim() {
        return dataNascim;
    }

    public void setDataNascim(Date dataNascim) {
        this.dataNascim = dataNascim;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getDdi2() {
        return ddi2;
    }

    public void setDdi2(String ddi2) {
        this.ddi2 = ddi2;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }               

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
            
    // Método para exibir informações do cliente
    public void exibirInformacoes() {
        System.out.println("ID do Cliente: " + idCliente);
        System.out.println("Nome do Cliente: " + nomeCompleto);
        System.out.println("Telefone do Cliente: " + telefone);
        System.out.println("Email do Cliente: " + emailCliente);
        System.out.println("Endereço: " + ", " + logradouro + ", " + complemento + ", CEP " + cep);        
        
    }

    public void adicionarCliente(Cliente cliente){
        application.MainApp.clienteController.criarCliente(cliente);
        ListaClientes = application.MainApp.clienteController.listarClientes();        
    } 
 
    public void salvarCliente(Cliente cliente){
        application.MainApp.clienteController.atualizarCliente(cliente);
        ListaClientes = application.MainApp.clienteController.listarClientes();        
    }

    public void removerCliente(Cliente cliente){
        application.MainApp.clienteController.deletarCliente(cliente.idCliente);
        ListaClientes = application.MainApp.clienteController.listarClientes();        
    }

    public List<Cliente> listarCliente(){        
        ListaClientes = application.MainApp.clienteController.listarClientes();        
        return ListaClientes;
    }     
    
}
