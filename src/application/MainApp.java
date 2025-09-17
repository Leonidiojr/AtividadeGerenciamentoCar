package application;

import static application.Database.checkDatabase;
import static utils.Utils.DialogBoxes.convertStringToDateTime;
import static utils.Utils.DialogBoxes.getCurrentDateTime;
import model.Acessorio;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


import controller.FuncionarioController;
import controller.ClienteController;
import controller.OrdemServicoController;
import controller.MesaController;
import controller.PagamentoController;
import controller.PedidoController;
import controller.PecaController;
import controller.enums.StatusOS;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Funcionario;
import model.Cliente;
import model.OrdemServico;
import model.Box;
import model.ItensPecas;
import model.ItensServicos;
import model.Marca;
import model.Modelo;
import model.Oficina;
import model.Pagamento;
import model.Pedido;
import model.Peca;
import model.Propriedade;
import model.Servico;
import model.Veiculo;
import static controller.enums.StatusOS.ABERTA;
import controller.enums.StatusPagamento;
import static controller.enums.StatusPagamento.ABERTO;
import java.awt.Cursor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import controller.AcessorioController;
import controller.ItensPecasController;
import controller.ItensServicosController;
import controller.MarcaController;
import controller.ModeloController;
import controller.OficinaController;
import controller.PropriedadeController;
import controller.ServicoController;
import controller.VeiculoController;
import view.TelaPrincipal;
import view.frmLoginDB;
import view.frmSplash;

public class MainApp{
       
    public final int numeroMesa = 0;
    
    //Cria controladores    
    public static final OficinaController oficinaController = new OficinaController();
    public static final FuncionarioController funcionarioController = new FuncionarioController();
    public static final ClienteController clienteController = new ClienteController();
    public static final MarcaController marcaController = new MarcaController();
    public static final ModeloController modeloController = new ModeloController();
    public static final ServicoController servicoController = new ServicoController();    
    public static final OrdemServicoController ordemServicoController = new OrdemServicoController();
    public static final MesaController boxController = new MesaController();
    public static final PagamentoController pagamentoController = new PagamentoController();
    public static final PedidoController pedidoController = new PedidoController();   
    public static final ItensPecasController itensPecasController = new ItensPecasController();   
    public static final ItensServicosController itensServicosController = new ItensServicosController();       
    public static final PecaController pecaController = new PecaController();   
    public static final VeiculoController veiculoController = new VeiculoController();   
    public static final AcessorioController acessorioController = new AcessorioController();   
    public static final PropriedadeController propriedadeController = new PropriedadeController(); 
    
    //Cria objetos de fluxo
    public static Oficina  oficinaAtiva= new Oficina("01.409.671/0001-73", "Oficina HeartSys", "ISENTO", "Rua das Bielas", "distrito industrial","74823342", "example@gmail.com", "+55", "062", "");
    public static Box boxAtivo = new Box(1,StatusOS.ABERTA);
    public static Funcionario funcionarioAtivo = new Funcionario("","","",0);
    public static Cliente clienteAtivo = new Cliente(0, "", "", "", "", "", "", "", "", "", "", 2, "", null, " " , "", "", "", "", "", "");                       
    public static Pedido pedidoAtivo = new Pedido(0,0,0,new BigDecimal(0.0));
    public static Peca pecaAtivo = new Peca(0,"","", 0, new BigDecimal(0.0),0);
    public static Servico servicoAtivo = new Servico(0,"", 1, new BigDecimal(0.0));    
    public static Veiculo veiculoAtivo = new Veiculo("", 0, 0, 0, 0, "", 0.0, null);
    public static Marca marcaAtiva = new Marca(0, "", "", null, 0);
    public static Modelo modeloAtiva = new Modelo(0, "", 0, "");
    public static Propriedade propriedadeAtiva = new Propriedade(0,"");        
    public static OrdemServico ordemServicoAtiva = new OrdemServico(
            0, // ID
            convertStringToDateTime(getCurrentDateTime()), // Data
            "N/A", // Placa
            BigDecimal.ZERO, // Valor Final
            BigDecimal.ZERO, // Valor Pago
            StatusOS.ABERTA, // Status da OS
            clienteAtivo,
            veiculoAtivo,
            funcionarioAtivo
    );                          
    public static Pagamento pagamentoAtivo = new Pagamento(
        0,  // idPagamento
        convertStringToDateTime(getCurrentDateTime()),  // dataPagamento
        0,  // numeroOrdemServico
        0.0,  // valorPago
        "1",  // tipoPagamento
        0.0,  // deslocamento
        0.0  // servicoGuincho
    );
        
    public static ItensPecas itensPecaAtiva = new ItensPecas(0, 0, 0.0, 0.0, "", "");
    public static ItensServicos itensServicoAtiva = new ItensServicos(0, 0, new Date(), new Date(), 0, 0, 0,0);
    public static Pagamento itensPagamentoAtiva = new Pagamento(0, LocalDateTime.now(), 0, 0.0, "", 0.0, 0.0);
    
    
        
    //Cria lista de objetos de fluxo
    public static List<Oficina> ListaOficinas = null;
    public static List<Cliente> ListaClientes = null;
    public static List<Funcionario> ListaFuncionarios = null;
    public static List<OrdemServico> ListaOrdensServicos = null;
    public static List<ItensPecas> ListaItensPecas = null;
    public static List<ItensServicos> ListaItensServicos = null;
    public static List<Box> ListaBoxes = null;
    public static List<Veiculo> ListaVeiculos = null;
    public static List<Propriedade> ListaPropriedades = null;
    public static List<Pagamento> ListaPagamentos = null;
    public static List<Pedido> ListaPedidos = null;
    public static List<Peca> ListaPecas = null;
    public static List<Marca> ListaMarcas = null;
    public static List<Modelo> ListaModelos = null;
    public static List<Acessorio> ListaAcessorios = null;
    public static List<Servico> ListaServicos = null;
    
    //Define conexao unica para o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.FULL_DB_URL, Constants.USER, Constants.PASSWORD);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }                                                                                                                
                
                if (checkDatabase()) {                    
                    JFrame TelaPrincipal = new TelaPrincipal();                                        
                    TelaPrincipal.setVisible(true);
                    //TelaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    
                } else {
                    JFrame frmLogin = new frmLoginDB();
                    frmLogin.setVisible(true);                                        
                }                                               
            }
        });
    }
    
    
}
    
