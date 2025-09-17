package model;

import static application.MainApp.ListaMarcas;
import java.util.List;
    
public class Marca {
    private int idMarcaPK;
    private String nome;
    private String tipo;
    private byte[] logo;
    private int idModeloFK;

    public Marca(int idMarcaPK, String nome, String tipo, byte[] logo, int idModeloFK) {
        this.idMarcaPK = idMarcaPK;
        this.nome = nome;
        this.tipo = tipo;
        this.logo = logo;
        this.idModeloFK = idModeloFK;
    }

    // Getters e Setters
    public int getIdMarcaPK() {
        return idMarcaPK;
    }

    public void setIdMarcaPK(int idMarcaPK) {
        this.idMarcaPK = idMarcaPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public int getIdModeloFK() {
        return idModeloFK;
    }

    public void setIdModeloFK(int idModeloFK) {
        this.idModeloFK = idModeloFK;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Marca{" +
                "idMarcaPK=" + idMarcaPK +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idModeloFK=" + idModeloFK +
                '}';
    }

    public void adicionarMarca(Marca marca){
        application.MainApp.marcaController.criarMarca(marca);
        ListaMarcas = application.MainApp.marcaController.listarMarcas();        
    } 
 
    public void salvarMarca(Marca marca){
        application.MainApp.marcaController.atualizarMarca(marca);
        ListaMarcas = application.MainApp.marcaController.listarMarcas();        
    }

    public void removerMarca(Marca marca){
        application.MainApp.marcaController.deletarMarca(marca.idMarcaPK);
        ListaMarcas = application.MainApp.marcaController.listarMarcas();        
    }

    public List<Marca> listarMarca(){        
        ListaMarcas = application.MainApp.marcaController.listarMarcas();        
        return ListaMarcas;
    }     
}
