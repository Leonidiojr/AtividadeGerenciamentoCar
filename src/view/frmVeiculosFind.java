/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import static application.MainApp.ListaVeiculos;
import static application.MainApp.veiculoAtivo;
import static application.MainApp.veiculoController;
import static application.MainApp.veiculoAtivo;
import static application.MainApp.veiculoController;
import model.Veiculo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author leoni
 */
public class frmVeiculosFind extends javax.swing.JDialog {

    /**
     * Creates new form frmFind
     */
    public frmVeiculosFind(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getContentPane().setBackground(Color.WHITE);

        // Adiciona a ação ao botão default
        jButton1.addActionListener(e -> {
            carregarVeiculos();
        });
        // Configura o InputMap e ActionMap para acionar o botão com Enter
        jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "clickButton");
        jButton1.getActionMap().put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1.doClick();
            }
        });
        
        
        // Adiciona a ação ao botão
        jButton2.addActionListener(e -> {
            redefineForm();
        });

        // Configura o InputMap e ActionMap para acionar o botão com Esc
        jButton2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "clickButton");
        jButton2.getActionMap().put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2.doClick();
            }
        });        
        
        redefineForm();
        
    }

    public void redefineForm(){
       
       // Obtemos o modelo da tabela
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
       model.setRowCount(0);  // Limpa as linhas da tabela
       this.jTextField1.setText("");
       this.jTextField1.requestFocusInWindow();
       this.jButton1.setEnabled(true);
       this.jButton2.setEnabled(true);
       this.jProgressBar1.setValue(0);
       this.jLabel3.setText("Parado");
    }    
    
public void carregarVeiculos() {

    this.jButton1.setEnabled(false);
    this.jButton2.setEnabled(true);
    this.jLabel3.setText("Procurando...");

    SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
        @Override
        protected Void doInBackground() throws Exception {
            boolean blnZero = false;

            // Lista os veiculos ativos
            ListaVeiculos = veiculoController.pesquisarVeiculos(jTextField1.getText());
            jProgressBar1.setMinimum(0);
            jProgressBar1.setMaximum(ListaVeiculos.size());

            // Verifica se a lista de veiculos não está vazia
            if (!ListaVeiculos.isEmpty()) {
                blnZero = true;
            } else {
                System.out.println("Nenhuma linha selecionada.");
            }

            int selectedRow = jTable1.getSelectedRow();

            // Obtemos o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);  // Limpa as linhas da tabela

            // Adiciona os dados de cada veiculo na tabela
            for (int i = 0; i < ListaVeiculos.size(); i++) {
                // Preenche os dados do veiculo na tabela
                publish(i); // Publica o progresso
                String[] coluna = new String[5];
                coluna[0] = String.valueOf(ListaVeiculos.get(i).getPlacaPK());
                coluna[1] = ListaVeiculos.get(i).getChassi();
                coluna[2] = Double.toString(ListaVeiculos.get(i).getKm());
                coluna[3] = Double.toString(ListaVeiculos.get(i).getNPatrimonio());
                coluna[4] = ""; // Adicione uma quinta coluna vazia, se necessário
                model.addRow(coluna);  // Adiciona a linha com os dados à tabela
            }

            // Verifica se o índice está dentro dos limites da tabela
            if (selectedRow >= 0 && selectedRow < jTable1.getRowCount()) {
                // Seleciona a linha especificada
                jTable1.setRowSelectionInterval(selectedRow, selectedRow);

                // Opcional: Desloca a tabela para a linha selecionada
                jTable1.scrollRectToVisible(jTable1.getCellRect(selectedRow, 0, true));
            } else {
                System.out.println("Índice da linha fora dos limites.");
            }

            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            for (int i : chunks) {
                jProgressBar1.setValue(i + 1);
            }
        }

        @Override
        protected void done() {
            jButton1.setEnabled(true);
        }
    };

    worker.execute();

    this.jLabel3.setText("Parado");
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar por");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.jpg"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jLabel1.setText("Procurar em:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes", "Funcionarios", "Veiculos", "Ordem de Servico", "Pagamentos" }));
        jComboBox1.setSelectedIndex(2);
        jComboBox1.setEnabled(false);

        jLabel2.setText("Valor procurado:");

        jButton1.setText("Pesquisar");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Parado");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "placa", "chassi", "km", "Patrimonio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable1MouseMoved(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTable1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(39, 39, 39)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(287, 287, 287)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(93, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                
        carregarVeiculos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.jButton1.isEnabled()) {
            veiculoAtivo = new Veiculo("", 0, 0, 0, 0, "", 0.0, null);             
        } else {
            redefineForm();
            jButton1.setEnabled(true);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseMoved

    }//GEN-LAST:event_jTable1MouseMoved

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        carregarVeiculos();
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        
    int selectedRow = jTable1.getSelectedRow(); // A linha selecionada

    if (selectedRow != -1) { // Verifica se uma linha foi selecionada
        try {
            // Pega o valor da célula da primeira coluna
            Object value = jTable1.getValueAt(selectedRow, 0);
            String idVeiculoAtivo = String.valueOf(value);

            if (idVeiculoAtivo != null && !idVeiculoAtivo.trim().isEmpty()) {
                // Realiza a busca pelo veiculo ativo
                veiculoAtivo = veiculoController.buscarVeiculoPorPlaca(idVeiculoAtivo);

                if (veiculoAtivo != null) {
                    // Abre a janela de edição de veiculos
                    veiculoAtivo = veiculoController.buscarVeiculoPorPlaca(veiculoAtivo.getPlacaPK());
                    this.dispose();

                    // Exibe o valor do ID do veiculo
                    System.out.println("ID Veiculo Ativo: " + idVeiculoAtivo);
                } else {
                    System.out.println("Veiculo não encontrado.");
                }
            } else {
                System.out.println("Valor do ID do veiculo é nulo ou vazio.");
            }
        } catch (NumberFormatException e) {
            // Trata a exceção caso o valor não seja um número válido
            System.out.println("Formato de número inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            // Trata a exceção caso o valor não seja do tipo esperado
            System.out.println("Valor do ID do veiculo inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Trata possíveis exceções ao buscar veiculo e abrir a janela
            System.out.println("Erro ao buscar veiculo ou abrir a janela: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        // Caso não haja nenhuma linha selecionada
        System.out.println("Nenhuma linha selecionada.");
    }
    carregarVeiculos();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTable1CaretPositionChanged

    }//GEN-LAST:event_jTable1CaretPositionChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVeiculosFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVeiculosFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVeiculosFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVeiculosFind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmVeiculosFind dialog = new frmVeiculosFind(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
