package model;

import java.util.Date;
import java.util.List;
import static application.MainApp.ListaItensServicos;

public class ItensServicos {
    private int idItensServPK;
    private int boxServico;
    private Date dataInicio;
    private Date dataFinalizado;
    private int qntd;
    private double precoUnit;
    private int idServicoFK;
    private int idOs;  // Adicionado campo idOs

    // Construtor
    public ItensServicos(int idItensServPK, int boxServico, Date dataInicio, Date dataFinalizado, int qntd, int idServicoFK, double precoUnit, int idOs) {
        this.idItensServPK = idItensServPK;
        this.boxServico = boxServico;
        this.dataInicio = dataInicio;
        this.dataFinalizado = dataFinalizado;
        this.qntd = qntd;
        this.idServicoFK = idServicoFK;
        this.precoUnit = precoUnit;
        this.idOs = idOs;  // Inicializando campo idOs
    }

    // Getters e Setters
    public int getIdItensServPK() {
        return idItensServPK;
    }

    public void setIdItensServPK(int idItensServPK) {
        this.idItensServPK = idItensServPK;
    }

    public int getBoxServico() {
        return boxServico;
    }

    public void setBoxServico(int boxServico) {
        this.boxServico = boxServico;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinalizado() {
        return dataFinalizado;
    }

    public void setDataFinalizado(Date dataFinalizado) {
        this.dataFinalizado = dataFinalizado;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public int getIdServicoFK() {
        return idServicoFK;
    }

    public void setIdServicoFK(int idServicoFK) {
        this.idServicoFK = idServicoFK;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "ItensServicos{" +
                "idItensServPK=" + idItensServPK +
                ", boxServico=" + boxServico +
                ", dataInicio=" + dataInicio +
                ", dataFinalizado=" + dataFinalizado +
                ", qntd=" + qntd +
                ", idServicoFK=" + idServicoFK +
                ", precoUnit=" + precoUnit +
                ", idOs=" + idOs +  // Exibir idOs no toString
                '}';
    }

    // Métodos de CRUD
    public void adicionarItensServicos(ItensServicos itensServicos){
        application.MainApp.itensServicosController.criarItensServicos(itensServicos);
        ListaItensServicos = application.MainApp.itensServicosController.listarItensServicos();        
    }

    public void salvarItensServicos(ItensServicos itensServicos){
        application.MainApp.itensServicosController.atualizarItensServicos(itensServicos);
        ListaItensServicos = application.MainApp.itensServicosController.listarItensServicos();        
    }

    public void removerItensServicos(ItensServicos itensServicos){
        application.MainApp.itensServicosController.deletarItensServicos(itensServicos.getIdItensServPK());
        ListaItensServicos = application.MainApp.itensServicosController.listarItensServicos();        
    }

    public List<ItensServicos> listarItensServicos(){        
        ListaItensServicos = application.MainApp.itensServicosController.listarItensServicos();        
        return ListaItensServicos;
    }         
}
