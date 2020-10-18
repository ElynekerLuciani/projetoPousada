/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import container.ContainerMenuCliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import view.TelaCadastroCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class ClienteController {
    private final ContainerMenuCliente menuCliente;

    public ClienteController(ContainerMenuCliente menuCliente) {
        this.menuCliente = menuCliente;
    }
    
    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                menuCliente.setTelaCadastroCliente(new TelaCadastroCliente());
                menuCliente.getTelaCadastroCliente().setBounds(5, 5, 200, 200);
                exibirJPanel(menuCliente.getTelaCadastroCliente());
                break;
        }
    }

    private void exibirJPanel(JPanel jPanel) {
        menuCliente.getjPanelCentro().removeAll();
        menuCliente.getjPanelCentro().add(jPanel, BorderLayout.CENTER);
        menuCliente.getjPanelCentro().revalidate();
        menuCliente.getjPanelCentro().repaint();
    }
    
    public void instanciar() {
         menuCliente.setTelaCadastroCliente(new TelaCadastroCliente());
         menuCliente.getTelaCadastroCliente().setBounds(5, 5, 200, 200);
         exibirJPanel(menuCliente.getTelaCadastroCliente());
    }
    

}
