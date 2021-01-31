package controller;

import container.ContainerMenuFinanceiro;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import view.TelaCaixaFinanceiro;
import view.TelaPesquisaCaixa;

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

    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Caixa":
                menuFinanceiro.setTelaCaixaFinanceiro(new TelaCaixaFinanceiro());
                menuFinanceiro.getTelaCaixaFinanceiro().setBounds(5, 5, 200, 200);
                exibirJPanel(menuFinanceiro.getTelaCaixaFinanceiro());
                break;
            case "Pesquisar":
                menuFinanceiro.setTelaPesquisaCaixa(new TelaPesquisaCaixa());
                menuFinanceiro.getTelaPesquisaCaixa().setBounds(5, 5, 200, 200);
                exibirJPanel(menuFinanceiro.getTelaPesquisaCaixa());
                break;
        }
    }

    private void caixaFinanceiro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
