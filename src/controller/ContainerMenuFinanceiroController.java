/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import container.ContainerMenuFinanceiro;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerMenuFinanceiroController {
    private final ContainerMenuFinanceiro menuFinanceiro;
    
    public ContainerMenuFinanceiroController(ContainerMenuFinanceiro menuFinanceiro) {
        this.menuFinanceiro = menuFinanceiro;
    }
    
    private void exibirJPanel(JPanel jPanel) {
        menuFinanceiro.getjPanelCentro().removeAll();
        menuFinanceiro.getjPanelCentro().add(jPanel, BorderLayout.CENTER);
        menuFinanceiro.getjPanelCentro().revalidate();
        menuFinanceiro.getjPanelCentro().repaint();
    }
    
}
