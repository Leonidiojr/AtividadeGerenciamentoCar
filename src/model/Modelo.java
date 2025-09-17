package model;

import static application.MainApp.ListaModelos;
import java.util.List;

public class Modelo {
    private int idModeloPK;
    private String nome;
    private int idMarca;
    private String tipo;

    public Modelo(int idModeloPK, String nome, int idMarca, String tipo) {
        this.idModeloPK = idModeloPK;
        this.nome = nome;
        this.idMarca = idMarca;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getIdModeloPK() {
        return idModeloPK;
    }

    public void setIdModeloPK(int idModeloPK) {
        this.idModeloPK = idModeloPK;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Modelo{" +
                "idModeloPK=" + idModeloPK +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idMarca=" + idMarca +                
                '}';
    }
    
    
    public void adicionarModelo(Modelo modelo){
        application.MainApp.modeloController.criarModelo(modelo);
        ListaModelos = application.MainApp.modeloController.listarModelos();        
    } 
 
    public void salvarModelo(Modelo modelo){
        application.MainApp.modeloController.atualizarModelo(modelo);
        ListaModelos = application.MainApp.modeloController.listarModelos();        
    }

    public void removerModelo(Modelo modelo){
        application.MainApp.modeloController.deletarModelo(modelo.idModeloPK);
        ListaModelos = application.MainApp.modeloController.listarModelos();        
    }

    public List<Modelo> listarModelo(){        
        ListaModelos = application.MainApp.modeloController.listarModelos();        
        return ListaModelos;
    }     
    
}
