package controller;

import container.ContainerMenuCliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import view.TelaCadastroCliente;
import view.TelaPesquisaCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerMenuClienteController {

    private static ContainerMenuClienteController principalMenuCliente;
    private ContainerMenuCliente menuCliente;
    private TelaCadastroCliente telaCadastroCliente;

    private ContainerMenuClienteController() {
        super();
    }

    public ContainerMenuClienteController(ContainerMenuCliente menuCliente) {
        this.menuCliente = menuCliente;
    }

    public void setTelaCadastroCliente(TelaCadastroCliente t) {
        this.telaCadastroCliente = t;
    }

    public void setMenuCliente(ContainerMenuCliente c) {
        this.menuCliente = c;
    }

    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar":
                menuCliente.setTelaCadastroCliente(new TelaCadastroCliente());
                menuCliente.getTelaCadastroCliente().setBounds(5, 5, 200, 200);
                exibirJPanel(menuCliente.getTelaCadastroCliente());
                break;
            case "Pesquisar":
                menuCliente.setTelaPesquisaCliente(new TelaPesquisaCliente());
                menuCliente.getTelaPesquisaCliente().setBounds(5, 5, 200, 200);
                exibirJPanel(menuCliente.getTelaPesquisaCliente());
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

    public void exibirTelaCadastroCliente() {
        menuCliente.setjPanelCentro(new TelaCadastroCliente());
        menuCliente.getTelaCadastroCliente().setBounds(5, 5, 200, 200);
        exibirJPanel(menuCliente.getTelaCadastroCliente());
    }

}
