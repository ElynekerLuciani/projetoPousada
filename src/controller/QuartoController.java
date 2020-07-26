package controller;

import componentes.QuartoComponent;
import container.ContainerQuarto;
import dao.QuartoDAO;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Quarto;
import view.InformarDadosHospedagem;
import view.PesquisaHospedarCliente;
import view.TelaHospedarCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoController {

    private static QuartoController controller;
    private ContainerQuarto container;
    private TelaHospedarCliente telaHospedarcliente;
    private final PesquisaHospedarCliente telaPesquisaHospedar = new PesquisaHospedarCliente();
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private String categoriaDoQuarto;

    public QuartoController() {
        super();
    }

    public static QuartoController getInstancia() {
        if (controller == null) {
            controller = new QuartoController();
        }
        return controller;
    }
    

    public void setQuartoController(ContainerQuarto c) {
        this.container = c;
    }
    
    public void setTelaHospedarCliente(TelaHospedarCliente t) {
        this.telaHospedarcliente = t;
    }
   
    /**
     * Método que realiza a busca dos quartos cadastrados e informa na tela
     * inicial se o quarto está livre ou ocupado.
     */
    public void exibirQuartoComponente() {
        try {
            ArrayList<Quarto> lista = quartoDAO.quartosDisponiveis();
            //Se existir quartos cadastrados
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    QuartoComponent quartoComponente = new QuartoComponent(lista.get(i));
                    exibirJPanel(quartoComponente);
                }
            } else {
                //Inicializa utilizando um construtor para quartos não
                //cadastrados
                QuartoComponent quartoComponente = new QuartoComponent();
                exibirJPanel(quartoComponente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.QuartoController.exibirQuartoComponente()"+ e);
        }
    }
    
    private void exibirJPanel(JPanel jPanel) {
        container.getjPanelCentro().add(jPanel);
        container.getjPanelCentro().revalidate();
        container.getjPanelCentro().repaint();
    }

    /**
     * Método que busca pelo o id do quarto o nome da categoria a que pertence
     * e retorna a categoria para ser informada na TelaHospedarCliente.
     * 
     * Passo a passo:
     * Este método é chamado dentro do construtor da TelaHospedarCliente, que
     * ao ser instanciada necessita exibir o nome da categoria do quarto.
     * @param idQuarto
     * @return 
     */
    public String buscarCategoriaQuarto(int idQuarto) {
        try {
            categoriaDoQuarto = quartoDAO.burcarCategoriaQuarto(idQuarto);
            if(!categoriaDoQuarto.isEmpty()) {
                return categoriaDoQuarto;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return "vazio";
    }

    public void exibirPesquisa() {
        try {
            PesquisaHospedarCliente p = new PesquisaHospedarCliente();
            exibirJPanelPesquisa(p);
        } catch (Exception e) {
            System.out.println("controller.QuartoController.exibirPesquisa()"+ e);
        }
       
        
    }
    
    private void exibirJPanelPesquisa(JPanel jPanel) {
       
        telaHospedarcliente.getjPanelCentral().removeAll();
        telaHospedarcliente.getjPanelCentral().add(jPanel, BorderLayout.CENTER);
        telaHospedarcliente.getjPanelCentral().revalidate();
        telaHospedarcliente.getjPanelCentral().repaint();
    }
    
    public void exibirPainelCentral(boolean b) {
         if(telaPesquisaHospedar != null && b) {
            telaHospedarcliente.setPesquisaHospedar(telaPesquisaHospedar);
            telaHospedarcliente.getPesquisaHospedar().setBounds(5, 5, 700, 420);
            exibirJPanelPesquisa(telaHospedarcliente.getPesquisaHospedar());
         } else {
            telaHospedarcliente.setInformarDadosHospedagem(new InformarDadosHospedagem());
            telaHospedarcliente.getInformarDadosHospedagem().setBounds(5, 5, 700, 420);
            exibirJPanelPesquisa(telaHospedarcliente.getInformarDadosHospedagem());
         }
         
         
       
        
    }
 
}
