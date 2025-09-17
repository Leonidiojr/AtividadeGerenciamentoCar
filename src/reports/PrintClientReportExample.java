package reports;

import static application.MainApp.clienteController;
import model.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.util.List;
import java.util.ArrayList;

public class PrintClientReportExample extends JFrame implements Printable {
    private JTextArea textArea;

    public PrintClientReportExample() {
        
        // Configuração do JFrame
        setTitle("Impressão de Relação de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Criação do JTextArea
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.BOLD, 14)); // Definir a fonte para Arial
        textArea.setText("Relação de Clientes\n\n");

        // Adicionar os dados dos clientes
        List<Cliente> clientes = clienteController.listarClientes();
        for (Cliente cliente : clientes) {
            textArea.append("ID: " + cliente.getIdCliente() + "  - Nome do cliente: " + cliente.getNomeCompleto() + "\n");            
        }

        // Adiciona o JTextArea ao JFrame
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Botão de Imprimir
        JButton printButton = new JButton("Imprimir Relação de Clientes");
        printButton.addActionListener(e -> printReport());
        getContentPane().add(printButton, BorderLayout.SOUTH);
    }

        // Método para imprimir o relatório
    private void printReport() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        // Define o tamanho da página para A4
        PageFormat pageFormat = job.defaultPage();
        Paper paper = new Paper();
        double width = 8.27 * 72; // 8.27 inch = 210 mm (largura A4 em pontos)
        double height = 11.69 * 72; // 11.69 inch = 297 mm (altura A4 em pontos)
        paper.setSize(width, height);
        paper.setImageableArea(0, 0, width, height); // área imprimível
        pageFormat.setPaper(paper);
        
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                System.out.println("Erro ao imprimir: " + ex.getMessage());
            }
        }
        dispose();
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        
        // Desenhar uma linha abaixo do título
        int yLinePosition = 30; // Posição Y da linha
        g2d.drawLine(0, yLinePosition, (int) pageFormat.getImageableWidth(), yLinePosition);

        // Imprimir o conteúdo do JTextArea abaixo da linha
        g2d.translate(0, yLinePosition + 10); // Ajusta a posição do texto abaixo da linha
        textArea.printAll(graphics);
        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrintClientReportExample example = new PrintClientReportExample();
            example.setVisible(true);
        });
    }
}


