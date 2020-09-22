package controller;

import componentes.QuartoComponent;
import container.ContainerQuarto;
import dao.QuartoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Quarto;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerQuartoController {
    private ContainerQuarto containerQuarto;
    private final QuartoDAO quartoDAO = new QuartoDAO();

    public ContainerQuartoController(ContainerQuarto containerQuarto) {
        this.containerQuarto = containerQuarto;
    }
    
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
        containerQuarto.getjPanelCentro().add(jPanel);
        containerQuarto.getjPanelCentro().revalidate();
        containerQuarto.getjPanelCentro().repaint();
    }
    
    
}
