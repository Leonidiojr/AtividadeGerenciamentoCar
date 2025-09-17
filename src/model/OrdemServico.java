package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import controller.enums.StatusOS;
import java.util.List;
import static application.MainApp.ListaOrdensServicos;

public class OrdemServico {

    private int id;
    private LocalDateTime data;
    private String placa;
    private BigDecimal valorFinal;
    private BigDecimal valorPago;
    private StatusOS status;
    private Cliente cliente;  
    private Veiculo veiculo;  
    private Funcionario funcionario;  

    // Construtor
    public OrdemServico(int id, LocalDateTime data, String placa, BigDecimal valorFinal, BigDecimal valorPago, StatusOS status, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
        this.id = id;
        this.data = data;
        this.placa = placa;
        this.valorFinal = valorFinal;
        this.valorPago = valorPago;
        this.status = status;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public StatusOS getStatus() {
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public Cliente getCliente() {  // Adicionado
        return cliente;
    }

    public void setCliente(Cliente cliente) {  // Adicionado
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {  // Adicionado
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {  // Adicionado
        this.veiculo = veiculo;
    }

    public Funcionario getFuncionario() {  // Adicionado
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {  // Adicionado
        this.funcionario = funcionario;
    }
    

    // Método para exibir informações da OS
    public void exibirInformacoes() {
        System.out.println("ID da OS: " + id);
        System.out.println("Data da OS: " + data);
        System.out.println("Placa: " + placa);
        System.out.println("Valor Final: " + valorFinal);
        System.out.println("Valor Pago: " + valorPago);
        System.out.println("Status da OS: " + status);
        System.out.println("Cliente: " + cliente.getNomeCompleto());
        System.out.println("Veículo: " +  veiculo.getModeloId());
        System.out.println("Funcionário: " + funcionario.getNomeComp());  // Exibindo funcionário
    }

    public void adicionarOrdemServico(OrdemServico ordemServico) {
        application.MainApp.ordemServicoController.criarOrdemServico(ordemServico);
        ListaOrdensServicos = application.MainApp.ordemServicoController.listarOrdemServico();
    }

    public void salvarOrdemServico(OrdemServico ordemServico) {
        application.MainApp.ordemServicoController.atualizarOrdemServico(ordemServico);
        ListaOrdensServicos = application.MainApp.ordemServicoController.listarOrdemServico();
    }

    public void removerOrdemServico(OrdemServico ordemServico) {
        application.MainApp.ordemServicoController.deletarOrdemServico(ordemServico.id);
        ListaOrdensServicos = application.MainApp.ordemServicoController.listarOrdemServico();
    }

    public List<OrdemServico> listarOrdemServico() {
        ListaOrdensServicos = application.MainApp.ordemServicoController.listarOrdemServico();
        return ListaOrdensServicos;
    }
}
