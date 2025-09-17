package model;

import static application.MainApp.ListaServicos;
import java.math.BigDecimal;
import java.util.List;

public class Servico {
    private int idServicoPK;
    private String descricao;
    private int qntd;
    private BigDecimal precoUnit;

    public Servico(int idServicoPK, String descricao, int qntd, BigDecimal precoUnit) {
        this.idServicoPK = idServicoPK;
        this.descricao = descricao;
        this.qntd = qntd;
        this.precoUnit = precoUnit;
    }

    // Getters e Setters
    public int getIdServicoPK() {
        return idServicoPK;
    }

    public void setIdServicoPK(int idServicoPK) {
        this.idServicoPK = idServicoPK;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Servico{" +
                "idServicoPK=" + idServicoPK +
                ", descricao='" + descricao + '\'' +
                ", qntd=" + qntd +
                ", precoUnit=" + precoUnit +
                '}';
    }
    
    public void adicionarServico(Servico servico){
        application.MainApp.servicoController.criarServico(servico);
        ListaServicos = application.MainApp.servicoController.listarServicos();        
    } 
 
    public void salvarServico(Servico servico){
        application.MainApp.servicoController.atualizarServico(servico);
        ListaServicos = application.MainApp.servicoController.listarServicos();        
    }

    public void removerServico(Servico servico){
        application.MainApp.servicoController.deletarServico(servico.idServicoPK);
        ListaServicos = application.MainApp.servicoController.listarServicos();        
    }

    public List<Servico> listarServico(){        
        ListaServicos = application.MainApp.servicoController.listarServicos();        
        return ListaServicos;
    } 
    
}
