package model;

import controller.enums.StatusOS; // Importa o enum StatusOS do pacote correto

public class Box {
    private int numeroBox;
    private StatusOS statusOSBox; // Alterado para o tipo do enum StatusOS
    private int boxAtivo;

    // Construtor
    public Box(int numeroMesa, StatusOS statusMesa) { // Ajustado para usar StatusOS
        this.numeroBox = numeroMesa;
        this.statusOSBox = statusMesa;
    }

    // Métodos getters e setters
    public int getNumeroMesa() {
        return numeroBox;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroBox = numeroMesa;
    }

    public StatusOS getStatusMesa() {
        return statusOSBox;
    }

    public void setStatusMesa(StatusOS statusMesa) {
        this.statusOSBox = statusMesa;
    }

    // Método para exibir informações da mesa
    public void exibirInformacoes() {
        System.out.println("Número da Mesa: " + numeroBox);
        System.out.println("Status da Mesa: " + statusOSBox);
    }

    public int getMesaAtiva() {
        return boxAtivo;
    }

    public void setMesaAtiva(int mesaAtiva) {
        this.boxAtivo = mesaAtiva;
    }
}
