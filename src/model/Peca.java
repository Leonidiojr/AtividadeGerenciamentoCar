package model;

import static application.MainApp.ListaPecas;
import java.math.BigDecimal;
import java.util.List;

public class Peca {
    private int idPecasPK;
    private String descricao;
    private String codFabricante;
    private int qntdEstoque;
    private BigDecimal precoUnit;
    private int idItensFK;

    public Peca(int idPecasPK, String descricao, String codFabricante, int qntdEstoque, BigDecimal precoUnit, int idItensFK) {
        this.idPecasPK = idPecasPK;
        this.descricao = descricao;
        this.codFabricante = codFabricante;
        this.qntdEstoque = qntdEstoque;
        this.precoUnit = precoUnit;
        this.idItensFK = idItensFK;
    }

    // Getters e Setters
    public int getIdPecasPK() {
        return idPecasPK;
    }

    public void setIdPecasPK(int idPecasPK) {
        this.idPecasPK = idPecasPK;
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

    public int getQntdEstoque() {
        return qntdEstoque;
    }

    public void setQntdEstoque(int qntdEstoque) {
        this.qntdEstoque = qntdEstoque;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }

    public int getIdItensFK() {
        return idItensFK;
    }

    public void setIdItensFK(int idItensFK) {
        this.idItensFK = idItensFK;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Peca{" +
                "idPecasPK=" + idPecasPK +
                ", descricao='" + descricao + '\'' +
                ", codFabricante='" + codFabricante + '\'' +
                ", qntdEstoque=" + qntdEstoque +
                ", precoUnit=" + precoUnit +
                ", idItensFK=" + idItensFK +
                '}';
    }
    
    public void adicionarPeca(Peca peca){
        application.MainApp.pecaController.criarPeca(peca);
        ListaPecas = application.MainApp.pecaController.listarPecas();        
    } 
 
    public void salvarPeca(Peca peca){
        application.MainApp.pecaController.atualizarPeca(peca);
        ListaPecas = application.MainApp.pecaController.listarPecas();        
    }

    public void removerPeca(Peca peca){
        application.MainApp.pecaController.deletarPeca(peca.idPecasPK);
        ListaPecas = application.MainApp.pecaController.listarPecas();        
    }

    public List<Peca> listarPeca(){        
        ListaPecas = application.MainApp.pecaController.listarPecas();        
        return ListaPecas;
    } 
    
}
