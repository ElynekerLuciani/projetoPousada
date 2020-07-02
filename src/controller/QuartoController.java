package controller;

import componentes.QuartoComponent;
import container.ContainerQuarto;
import javax.swing.JPanel;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoController {
    private static QuartoController controller;
    private ContainerQuarto container;
    
    
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
  
}
