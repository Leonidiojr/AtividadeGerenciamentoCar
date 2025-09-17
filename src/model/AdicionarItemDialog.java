package model;

import static application.MainApp.itensServicoAtiva;
import static application.MainApp.itensServicosController;
import static application.MainApp.ordemServicoAtiva;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import controller.OrdemServicoController;

public class AdicionarItemDialog extends JDialog {

    private JComboBox<String> comboBoxServicos;
    private JComboBox<String> comboBoxVeiculos;
    private JComboBox<String> comboBoxFuncionarios;
    private JComboBox<String> comboBoxClientes;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    private OrdemServicoController ordemServicoController;

    public AdicionarItemDialog(Frame parent, OrdemServicoController ordemServicoController) {
        super(parent, "Adicionar Item à Ordem de Serviço", true);
        this.ordemServicoController = ordemServicoController;

        // Criação dos componentes
        comboBoxServicos = new JComboBox<>(getNomesServicos());
        comboBoxVeiculos = new JComboBox<>(getPlacasVeiculos());
        comboBoxFuncionarios = new JComboBox<>(getCPFsFuncionarios());
        comboBoxClientes = new JComboBox<>(getNomesClientes());

        btnAdicionar = new JButton("Adicionar");
        btnCancelar = new JButton("Cancelar");

        // Configuração do layout
        setLayout(new GridLayout(5, 2, 10, 10));
        add(new JLabel("Serviço:"));
        add(comboBoxServicos);
        add(new JLabel("Veículo:"));
        add(comboBoxVeiculos);
        add(new JLabel("Funcionário:"));
        add(comboBoxFuncionarios);
        add(new JLabel("Cliente:"));
        add(comboBoxClientes);
        add(btnAdicionar);
        add(btnCancelar);

        // Configuração dos eventos dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }

    private String[] getNomesServicos() {
        // Substitua com a lógica para obter os nomes dos serviços do banco de dados
        List<String> servicos = new ArrayList<>();
        servicos.add("Alinhamento");
        // Adicione outros serviços conforme necessário
        return servicos.toArray(new String[0]);
    }

    private String[] getPlacasVeiculos() {
        // Substitua com a lógica para obter as placas dos veículos do banco de dados
        List<String> veiculos = new ArrayList<>();
        veiculos.add("AAA-1234");
        // Adicione outras placas conforme necessário
        return veiculos.toArray(new String[0]);
    }

    private String[] getCPFsFuncionarios() {
        // Substitua com a lógica para obter os CPFs dos funcionários do banco de dados
        List<String> funcionarios = new ArrayList<>();
        funcionarios.add("111.222.333-44");
        // Adicione outros CPFs conforme necessário
        return funcionarios.toArray(new String[0]);
    }

    private String[] getNomesClientes() {
        // Substitua com a lógica para obter os nomes dos clientes do banco de dados
        List<String> clientes = new ArrayList<>();
        clientes.add("João Silva");
        // Adicione outros clientes conforme necessário
        return clientes.toArray(new String[0]);
    }

    private void adicionarItem() {
        String servicoSelecionado = (String) comboBoxServicos.getSelectedItem();
        String veiculoSelecionado = (String) comboBoxVeiculos.getSelectedItem();
        String funcionarioSelecionado = (String) comboBoxFuncionarios.getSelectedItem();
        String clienteSelecionado = (String) comboBoxClientes.getSelectedItem();
        
                
        //itensServicoAtiva.setIdServicoFK(servicoSelecionado);        
                
        // Exemplo de uso do ordemServicoController para adicionar o item (substitua conforme necessário)
        itensServicosController.criarItensServicos(itensServicoAtiva);

        dispose();
    }

    
}
