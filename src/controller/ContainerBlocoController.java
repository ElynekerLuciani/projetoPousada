package controller;

import componentes.BlocoComponent;
import container.ContainerBloco;
import dao.QuartoDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Quarto;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerBlocoController {
    private final ContainerBloco containerBloco;
    private final QuartoDAO quartoDAO = new QuartoDAO();

    //construtor da classe container bloco controller
    public ContainerBlocoController(ContainerBloco containerQuarto) {
        this.containerBloco = containerQuarto;
    }
    
    public void exibirBlocoComponente() {
        try {
            ArrayList<Quarto> lista = quartoDAO.quartosDisponiveis();
            //Se existir quartos cadastrados
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    BlocoComponent blocoComponente = new BlocoComponent(lista.get(i));
                    exibirJPanel(blocoComponente);
                }
            } else {
                //Inicializa utilizando um construtor para quartos não
                //cadastrados
                BlocoComponent blocoComponente = new BlocoComponent();
                exibirJPanel(blocoComponente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.ContainerBlocoController.exibirBlocoComponente()"+ e);
        }
    }
    
     private void exibirJPanel(JPanel jPanel) {
        containerBloco.getjPanelCentro().add(jPanel);
        containerBloco.getjPanelCentro().revalidate();
        containerBloco.getjPanelCentro().repaint();
    }
    
}
