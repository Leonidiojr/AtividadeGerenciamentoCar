package model;

import static application.MainApp.ListaItensPecas;
import java.util.List;

public class ItensPecas {
    private int idItensPK;
    private int qntd;
    private double precoUnit;
    private double precoTotal;
    private String descricao;
    private String codFabricante;

    public ItensPecas(int idItensPK, int qntd, double precoUnit, double precoTotal, String descricao, String codFabricante) {
        this.idItensPK = idItensPK;
        this.qntd = qntd;
        this.precoUnit = precoUnit;
        this.precoTotal = precoTotal;
        this.descricao = descricao;
        this.codFabricante = codFabricante;
    }

    // Getters e Setters
    public int getIdItensPK() {
        return idItensPK;
    }

    public void setIdItensPK(int idItensPK) {
        this.idItensPK = idItensPK;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(String codFabricante) {
        this.codFabricante = codFabricante;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "ItensPecas{" +
                "idItensPK=" + idItensPK +
                ", qntd=" + qntd +
                ", precoUnit=" + precoUnit +
                ", precoTotal=" + precoTotal +
                ", descricao='" + descricao + '\'' +
                ", codFabricante='" + codFabricante + '\'' +
                '}';
    }
    
    public void adicionarItensPecas(ItensPecas itensPecas){
        application.MainApp.itensPecasController.criarItensPecas(itensPecas);
        ListaItensPecas = application.MainApp.itensPecasController.listarItensPecas();        
    } 
 
    public void salvarItensPecas(ItensPecas itensPecas){
        application.MainApp.itensPecasController.atualizarItensPecas(itensPecas);
        ListaItensPecas = application.MainApp.itensPecasController.listarItensPecas();        
    }

    public void removerItensPecas(ItensPecas itensPecas){
        application.MainApp.itensPecasController.deletarItensPecas(itensPecas.getIdItensPK());
        ListaItensPecas = application.MainApp.itensPecasController.listarItensPecas();        
    }

    public List<ItensPecas> listarItensPecas(){        
        ListaItensPecas = application.MainApp.itensPecasController.listarItensPecas();        
        return ListaItensPecas;
    }         
}
