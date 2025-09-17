package model;

import static application.MainApp.ListaPropriedades;
import java.util.List;

public class Propriedade {
    private int idClienteFK;
    private String placaFK;

    public Propriedade(int idClienteFK, String placaFK) {
        this.idClienteFK = idClienteFK;
        this.placaFK = placaFK;
    }

    // Getters e Setters
    public int getIdClienteFK() {
        return idClienteFK;
    }

    public void setIdClienteFK(int idClienteFK) {
        this.idClienteFK = idClienteFK;
    }

    public String getPlacaFK() {
        return placaFK;
    }

    public void setPlacaFK(String placaFK) {
        this.placaFK = placaFK;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Propriedade{" +
                "idClienteFK=" + idClienteFK +
                ", placaFK='" + placaFK + '\'' +
                '}';
    }
    
    public void adicionarPropriedade(Propriedade propriedade){
        application.MainApp.propriedadeController.criarPropriedade(propriedade);
        ListaPropriedades = application.MainApp.propriedadeController.listarPropriedades();        
    } 
 
    public void salvarPropriedade(Propriedade propriedade){
        application.MainApp.propriedadeController.atualizarPropriedade(propriedade);
        ListaPropriedades = application.MainApp.propriedadeController.listarPropriedades();        
    }

    public void removerPropriedade(Propriedade propriedade){
        application.MainApp.propriedadeController.deletarPropriedade(idClienteFK, placaFK);
        ListaPropriedades = application.MainApp.propriedadeController.listarPropriedades();        
    }

    public List<Propriedade> listarPropriedade(){        
        ListaPropriedades = application.MainApp.propriedadeController.listarPropriedades();        
        return ListaPropriedades;
    } 
}
