package controller;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import container.ContainerQuarto;
import view.TelaPrincipal;

/**
 *
 * @author Elyneker Luciani
 */
public class PrincipalController {
    private static PrincipalController principal;
    private TelaPrincipal telaInicial;
    private ContainerQuarto quartoComponente;
    
    private PrincipalController(){
        super();
    }
    
    public static PrincipalController getInstancia(){
        if(principal == null){
            principal = new PrincipalController();
        }
        return principal;
    }
    
    /**
     * Neste método atribuimos uma TelaPrincipal ao atributo privado 
     * desta classe.
     * @param tela
     */
    public void setPrincipal(TelaPrincipal tela) {
        this.telaInicial = tela;
    }
    
    public void setQuartoComponente(ContainerQuarto quartoComponente) {
        this.quartoComponente = quartoComponente;
    }
    
    /**
     * Neste método instanciamos o ContainerQuarto responsável por
     * apresentar as views na tela principal.
    */
    public void exibirContainerQuarto() {
        telaInicial.setJPanelContainerQuarto(new ContainerQuarto());
        telaInicial.getJPanelContainerQuarto().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getJPanelContainerQuarto());
    }

    private void exibirJPanel(JPanel jPanel) {
        telaInicial.getjPanelPrincipal().removeAll();
        telaInicial.getjPanelPrincipal().add(jPanel, BorderLayout.CENTER);
        telaInicial.getjPanelPrincipal().revalidate();
        telaInicial.getjPanelPrincipal().repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
    
}
