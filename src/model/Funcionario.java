package model;

import static application.MainApp.ListaFuncionarios;
import java.util.List;

public class Funcionario {
    private String CPF;
    private String nomeComp;
    private String departamento;
    private int iDitensServFK;

    //Construtor
    public Funcionario(String CPF, String nomeComp, String departamento, int iDitensServFK) {
        this.CPF = CPF;
        this.nomeComp = nomeComp;
        this.departamento = departamento;
        this.iDitensServFK = iDitensServFK;
    }

    // Getters e setters

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeComp() {
        return nomeComp;
    }

    public void setNomeComp(String nomeComp) {
        this.nomeComp = nomeComp;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getiDitensServFK() {
        return iDitensServFK;
    }

    public void setiDitensServFK(int iDitensServFK) {
        this.iDitensServFK = iDitensServFK;
    }


    // Método para exibir informações do funcionario

    
    @Override
    public String toString(){
        return "Funcionario{" + "CPF=" + CPF + ", nomeComp=" + nomeComp + ", departamento=" + departamento + ", iDitensServFK=" + iDitensServFK + '}';        
    } 

    public void adicionarFuncionario(Funcionario funcionario) {
        application.MainApp.funcionarioController.criarFuncionario(funcionario);
        ListaFuncionarios = application.MainApp.funcionarioController.listarFuncionarios();
    }
 
    public void salvarFuncionario(Funcionario funcionario){
        application.MainApp.funcionarioController.atualizarFuncionario(funcionario);
        ListaFuncionarios = application.MainApp.funcionarioController.listarFuncionarios();        
    }

    public void removerFuncionario(Funcionario funcionario){
        application.MainApp.funcionarioController.deletarFuncionario(funcionario.CPF);
        ListaFuncionarios = application.MainApp.funcionarioController.listarFuncionarios();        
    }

    public List<Funcionario> listarFuncionario(){        
        ListaFuncionarios = application.MainApp.funcionarioController.listarFuncionarios();        
        return ListaFuncionarios;
    }     
    
}
