package model;

import static application.MainApp.ListaPedidos;
import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private int idPedido;
    private int idProduto;
    private int quantidadeProduto;
    private BigDecimal valorUnitarioProduto;
    private BigDecimal totalProdutoPedido;

    // Construtor
    public Pedido(int idPedido, int idProduto, int quantidadeProduto, BigDecimal valorUnitarioProduto) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.valorUnitarioProduto = valorUnitarioProduto;
        calcularTotalProdutoPedido();
    }

    // Método para calcular o total do pedido
    private void calcularTotalProdutoPedido() {
        totalProdutoPedido = valorUnitarioProduto.multiply(BigDecimal.valueOf(quantidadeProduto));
    }

    // Métodos getters e setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
        calcularTotalProdutoPedido();
    }

    public BigDecimal getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(BigDecimal valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
        calcularTotalProdutoPedido();
    }

    public BigDecimal getTotalProdutoPedido() {
        return totalProdutoPedido;
    }

    // Método para exibir informações do pedido
    public void exibirInformacoes() {
        System.out.println("ID do Pedido: " + idPedido);
        System.out.println("ID do Produto: " + idProduto);
        System.out.println("Quantidade do Produto: " + quantidadeProduto);
        System.out.println("Valor Unitário do Produto: " + valorUnitarioProduto);
        System.out.println("Total do Pedido: " + totalProdutoPedido);
    }
    
    public void adicionarPedido(Pedido pedido){
        application.MainApp.pedidoController.criarPedido(pedido);
        ListaPedidos = application.MainApp.pedidoController.listarPedidos();        
    } 
 
    public void salvarPedido(Pedido pedido){
        application.MainApp.pedidoController.atualizarPedido(pedido);
        ListaPedidos = application.MainApp.pedidoController.listarPedidos();        
    }

    public void removerPedido(Pedido pedido){
        application.MainApp.pedidoController.deletarPedido(pedido.idPedido);
        ListaPedidos = application.MainApp.pedidoController.listarPedidos();        
    }

    public List<Pedido> listarPedido(){        
        ListaPedidos = application.MainApp.pedidoController.listarPedidos();        
        return ListaPedidos;
    } 
    
}
