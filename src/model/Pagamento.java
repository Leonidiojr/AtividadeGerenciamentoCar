package model;

import static application.MainApp.ListaPagamentos;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pagamento {
    private int idPagamento;
    private LocalDateTime dataPagamento;
    private int numeroOrdemServico;
    private double valorPago;
    private String tipoPagamento;
    private double deslocamento;
    private double servicoGuincho;

    public Pagamento(int idPagamento, LocalDateTime dataPagamento, int numeroOrdemServico, double valorPago, String tipoPagamento, double deslocamento, double servicoGuincho) {
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.numeroOrdemServico = numeroOrdemServico;
        this.valorPago = valorPago;
        this.tipoPagamento = tipoPagamento;
        this.deslocamento = deslocamento;
        this.servicoGuincho = servicoGuincho;
    }

    // Getters e Setters
    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getNumeroOrdemServico() {
        return numeroOrdemServico;
    }

    public void setNumeroOrdemServico(int numeroOrdemServico) {
        this.numeroOrdemServico = numeroOrdemServico;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(double deslocamento) {
        this.deslocamento = deslocamento;
    }

    public double getServicoGuincho() {
        return servicoGuincho;
    }

    public void setServicoGuincho(double servicoGuincho) {
        this.servicoGuincho = servicoGuincho;
    }
    
    public void adicionarPagamento(model.Pagamento pagamento){
        application.MainApp.pagamentoController.criarPagamento(pagamento);
        ListaPagamentos = application.MainApp.pagamentoController.listarPagamentos();        
    } 
 
    public void salvarPagamento(model.Pagamento pagamento){
        application.MainApp.pagamentoController.atualizarPagamento(pagamento);
        ListaPagamentos = application.MainApp.pagamentoController.listarPagamentos();        
    }

    public void removerPagamento(model.Pagamento pagamento){
        application.MainApp.pagamentoController.deletarPagamento(pagamento.idPagamento);
        ListaPagamentos = application.MainApp.pagamentoController.listarPagamentos();        
    }

    public List<model.Pagamento> listarPagamento(){        
        ListaPagamentos = application.MainApp.pagamentoController.listarPagamentos();        
        return ListaPagamentos;
    }
}
