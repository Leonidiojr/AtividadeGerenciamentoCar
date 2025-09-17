package model;

import static application.MainApp.ListaAcessorios;
import java.util.List;


public class Acessorio {
    private int idAcessorioPK;
    private String descricao;

    public Acessorio(int idAcessorioPK, String descricao) {
        this.idAcessorioPK = idAcessorioPK;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdAcessorioPK() {
        return idAcessorioPK;
    }

    public void setIdAcessorioPK(int idAcessorioPK) {
        this.idAcessorioPK = idAcessorioPK;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Acessorio{" +
                "idAcessorioPK=" + idAcessorioPK +
                ", descricao='" + descricao + '\'' +
                '}';
    }

public void adicionarAcessorio(Acessorio acessorio){
        application.MainApp.acessorioController.criarAcessorio(acessorio);
        ListaAcessorios = application.MainApp.acessorioController.listarAcessorios();        
    } 
 
    public void salvarAcessorio(Acessorio acessorio){
        application.MainApp.acessorioController.atualizarAcessorio(acessorio);
        ListaAcessorios = application.MainApp.acessorioController.listarAcessorios();        
    }

    public void removerAcessorio(Acessorio acessorio){
        application.MainApp.acessorioController.deletarAcessorio(acessorio.getIdAcessorioPK());
        ListaAcessorios = application.MainApp.acessorioController.listarAcessorios();        
    }

    public List<Acessorio> listarAcessorio(){        
        ListaAcessorios = application.MainApp.acessorioController.listarAcessorios();        
        return ListaAcessorios;
    }         
    
}
