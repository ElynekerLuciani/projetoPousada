package controller;

import componentes.QuartoComponent;
import container.ContainerQuarto;
import dao.QuartoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Quarto;
import view.TelaHospedarCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoController {

    private static QuartoController controller;
    private ContainerQuarto container;
    private QuartoDAO quartoDAO = new QuartoDAO();

    private QuartoController() {
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
                    //QuartoComponent quartoComponente = new QuartoComponent(lista.get(i).getNumeroQuarto(), lista.get(i).getStatusQuarto());
                    exibirJPanel(quartoComponente);
                    //System.out.println(lista.get(i).getNumeroQuarto() + lista.get(i).getStatusQuarto().toString());
                }
            } else {
                //Inicializa utilizando um construtor para quartos não
                //cadastrados
                QuartoComponent quartoComponente = new QuartoComponent();
                exibirJPanel(quartoComponente);
            }
        } catch (Exception e) {
        }
    }
    
    public void exibirPainelTeste() {
        TelaHospedarCliente cadastroRapido = new TelaHospedarCliente();
        exibirJPanel(cadastroRapido);
    }

    private void exibirJPanel(JPanel jPanel) {
        container.getjPanelCentro().add(jPanel);
        container.getjPanelCentro().revalidate();
        container.getjPanelCentro().repaint();
    }

    public void buscarStatusQuarto() throws SQLException, ClassNotFoundException {

//         try {
//             if(lista.isEmpty()) {
//                 for(int i = 0; i < lista.size(); i++) {
//                     System.out.println(lista.get(i).getNumeroQuarto() + lista.get(i).getStatusQuarto().toString());
//                }
//             
//                 
//             
//         }
//         } catch (Exception e) {
//         }
    }

}
