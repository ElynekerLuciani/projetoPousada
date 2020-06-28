/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import componentes.ContainerQuartoComponente;
import view.TelaPrincipal;

/**
 *
 * @author Elyneker Luciani
 */
public class PrincipalController {
    private static PrincipalController principal;
    private TelaPrincipal telaInicial;
    
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
     * Neste métodoatribuimos uma TelaPrincipal ao atributo privado
     * TelaPrincipal desta classe.
     */
    public void setPrincipal(TelaPrincipal principal) {
        this.telaInicial = principal;
    }
    
    
    /**
     * Neste método instanciamos o ContainerQuartoComponente responsável por
     * apresentar as views na tela principal.
    */
    public void exibirContainerQuarto() {
        telaInicial.setJPanelContainerQuarto(new ContainerQuartoComponente());
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
