/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import container.ContainerMenuHospedagem;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import view.TelaPesquisaHistorico;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerMenuHospedagemController {
    private final ContainerMenuHospedagem menuHospedagem;

    public ContainerMenuHospedagemController(ContainerMenuHospedagem menuHospedagem) {
        this.menuHospedagem = menuHospedagem;
    }
    
    public void executa(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "Histórico":
                menuHospedagem.setTelaPesquisaHistorico(new TelaPesquisaHistorico());
                menuHospedagem.getTelaPesquisaHistorico().setBounds(5, 5, 200, 200);
                exibirJPanel(menuHospedagem.getTelaPesquisaHistorico());
                break;
        }
    }
    
    private void exibirJPanel(JPanel jPanel) {
        menuHospedagem.getjPanelCentro().removeAll();
        menuHospedagem.getjPanelCentro().add(jPanel, BorderLayout.CENTER);
        menuHospedagem.getjPanelCentro().revalidate();
        menuHospedagem.getjPanelCentro().repaint();
    }
    
}
