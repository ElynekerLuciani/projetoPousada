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
public class QuartoController {
    private static QuartoController controller;
    private ContainerQuarto container;
    private QuartoDAO quartoDAO = new QuartoDAO();
    
    
    private QuartoController() {
        super();
    }
    
    public static QuartoController getInstancia() {
        if(controller == null) {
            controller = new QuartoController();
        }
        return controller;
    }
    
    public void setQuartoController(ContainerQuarto c) {
        this.container = c;
    
    }
    
     public void exibirQuartoComponente() {
         try {
             for (int i = 1; i <= 30; i++) {
                 QuartoComponent quartoComponente = new QuartoComponent(i);
                 exibirJPanel(quartoComponente);
             }
         } catch (Exception e) {
         }   
    }
     
      private void exibirJPanel(JPanel jPanel) {
          container.getjPanelCentro().add(jPanel);
         container.getjPanelCentro().add(jPanel);
         container.getjPanelCentro().revalidate();
         container.getjPanelCentro().repaint();
     }
      
     public void buscarStatusQuarto() throws SQLException, ClassNotFoundException {
         ArrayList<Quarto> lista = quartoDAO.quartosDisponiveis();
         try {
             for(int i = 0; i < lista.size(); i++) {
                 System.out.println(lista.get(i).getNumeroQuarto() + lista.get(i).getStatusQuarto().toString());
                 
             
         }
         } catch (Exception e) {
         }
     }
  
}
