package controller;

import container.ContainerMenuConfigurar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import view.TelaCadastroProduto;
import view.TelaValoresQuartos;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerMenuConfigurarController {

    private final ContainerMenuConfigurar menuConfigurar;

    public ContainerMenuConfigurarController(ContainerMenuConfigurar menuConfigurar) {
        this.menuConfigurar = menuConfigurar;
    }

    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Cadastrar Produto":
                menuConfigurar.setTelaProduto(new TelaCadastroProduto());
                menuConfigurar.getTelaProduto().setBounds(5, 5, 200, 200);
                exibirJPanel(menuConfigurar.getTelaProduto());
                break;
            case "Valores Quarto":
                menuConfigurar.setValorQuarto(new TelaValoresQuartos());
                menuConfigurar.getValorQuarto().setBounds(5, 5, 200, 200);
                exibirJPanel(menuConfigurar.getValorQuarto());
                break;
        }
    }

    private void exibirJPanel(JPanel jPanel) {
        menuConfigurar.getjPanelCentro().removeAll();
        menuConfigurar.getjPanelCentro().add(jPanel, BorderLayout.CENTER);
        menuConfigurar.getjPanelCentro().revalidate();
        menuConfigurar.getjPanelCentro().repaint();
    }
}
