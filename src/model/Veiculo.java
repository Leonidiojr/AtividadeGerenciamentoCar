package model;

import static application.MainApp.ListaVeiculos;
import java.util.List;

public class Veiculo {

    private String placaPK;
    private int marcaId;
    private int modeloId;
    private int anoFabricacao;
    private int anoModelo;
    private String chassi;
    private double km;
    private Integer nPatrimonio;

    public Veiculo(String placaPK, int marcaId, int modeloId, int anoFabricacao, int anoModelo, String chassi, double km, Integer nPatrimonio) {
        this.placaPK = placaPK;
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.chassi = chassi;
        this.km = km;
        this.nPatrimonio = nPatrimonio;
    }

    // Getters e Setters
    public String getPlacaPK() {
        return placaPK;
    }

    public void setPlacaPK(String placaPK) {
        this.placaPK = placaPK;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public int getModeloId() {
        return modeloId;
    }

    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public Integer getNPatrimonio() {
        return nPatrimonio;
    }

    public void setNPatrimonio(Integer nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placaPK='" + placaPK + '\'' +
                ", marcaId=" + marcaId +
                ", modeloId=" + modeloId +
                ", anoFabricacao=" + anoFabricacao +
                ", anoModelo=" + anoModelo +
                ", chassi='" + chassi + '\'' +
                ", km=" + km +
                ", nPatrimonio=" + nPatrimonio +
                '}';
    }

    
    public void adicionarVeiculo(Veiculo veiculo){
        application.MainApp.veiculoController.criarVeiculo(veiculo);
        ListaVeiculos = application.MainApp.veiculoController.listarVeiculos();        
    } 
 
    public void salvarVeiculo(Veiculo veiculo){
        application.MainApp.veiculoController.atualizarVeiculo(veiculo);
        ListaVeiculos = application.MainApp.veiculoController.listarVeiculos();        
    }

    public void removerVeiculo(Veiculo veiculo){
        application.MainApp.veiculoController.deletarVeiculo(veiculo.placaPK);
        ListaVeiculos = application.MainApp.veiculoController.listarVeiculos();        
    }

    public List<Veiculo> listarVeiculo(){        
        ListaVeiculos = application.MainApp.veiculoController.listarVeiculos();        
        return ListaVeiculos;
    }     
}
