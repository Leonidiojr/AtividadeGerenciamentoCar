
package model;

import static application.MainApp.ListaOficinas;
import java.util.List;

/**
 *
 * @author leoni
 */
public class Oficina {
    private String CNPJ;
    private String razaoSocial;
    private String IE;
    private String logradouro;
    private String complemento;
    private String CEP;
    private String email;
    private String ddi;
    private String ddd;
    private String numero;

    // Construtor
    public Oficina(String CNPJ, String razaoSocial, String IE, String logradouro, String complemento, String CEP, String email, String ddi, String ddd, String numero) {
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.IE = IE;
        this.logradouro = logradouro; 
        this.complemento = complemento;
        this.CEP = CEP;
        this.email = email;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    // Getters e Setters
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
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

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Oficina{" +
                "CNPJ='" + CNPJ + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", IE=" + IE +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", CEP='" + CEP + '\'' +
                ", email='" + email + '\'' +
                ", ddi='" + ddi + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
    
    public void adicionarOficina(Oficina oficina) {
        application.MainApp.oficinaController.criarOficina(oficina);
        ListaOficinas = application.MainApp.oficinaController.listarOficinas();
    }
 
    public void salvarOficina(Oficina oficina){
        application.MainApp.oficinaController.atualizarOficina(oficina);
        ListaOficinas = application.MainApp.oficinaController.listarOficinas();        
    }

    public void removerOficina(Oficina oficina){
        application.MainApp.oficinaController.deletarOficina(oficina.getCNPJ());
        ListaOficinas = application.MainApp.oficinaController.listarOficinas();        
    }

    public List<Oficina> listarOficina(){        
        ListaOficinas = application.MainApp.oficinaController.listarOficinas();        
        return ListaOficinas;
    }     
    
}
