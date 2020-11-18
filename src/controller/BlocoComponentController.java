package controller;

import componentes.BlocoComponent;
import dao.QuartoDAO;
import dao.ReservaDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 *
 * @author Elyneker Luciani
 */
public class BlocoComponentController {

    private BlocoComponent component = new BlocoComponent();
    private static final Color COR_QUARTO_LIVRE = new Color(255, 255, 255);
    private static final Color COR_QUARTO_OCUPADO = new Color(248, 215, 218);
    private static final Color COR_QUARTO_SUJO = new Color(254, 153, 0);
    private final PrincipalController principal = PrincipalController.getInstancia();
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();

    public BlocoComponentController(BlocoComponent component) {
        this.component = component;
    }

    /**
     * Este método centraliza as ações de clique no botão do bloco componente
     * pegando o evento do clique e lendo o título do botão para poder chamar o
     * método adequado para a execução do evento.
     *
     * @param evt
     */
    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
            case "Informações":
                exibirInformacaoDeReserva();
                break;
            case "Limpar":
                limparQuarto();
                break;
        }
    }

    /**
     * Este método é responsável por alterar o status do bloco componente.
     * Utilizando cores padronizadas para cada status, o método recebe o status
     * do quarto e a informação se o quarto está sujo. Se o status do quarto for
     * ativo, então é verificado se o quarto está sujo ou não. Caso contrário, o
     * quarto está livre.
     *
     * @param statusQuarto
     * @param sujo
     */
    public void alterarStatus(boolean statusQuarto, boolean sujo) {
        if (statusQuarto) {
            if (sujo) {
                component.setBackground(COR_QUARTO_SUJO);
                component.getStatus().setText("Sujo");
                component.getBtnLimpar().setVisible(true);
                component.getBtnLimpar().setText("Limpar");
            } else {
                component.setBackground(COR_QUARTO_OCUPADO);
                component.getStatus().setText("Ocupado");
                component.getBtnLimpar().setVisible(true);
                component.getBtnLimpar().setText("Informações");
            }
        } else {
            component.setBackground(COR_QUARTO_LIVRE);
            component.getStatus().setText("Livre");
            component.getBtnLimpar().setText("Hospedar");
            component.getBtnLimpar().setVisible(true);
        }
    }

    /**
     * Este método é responsável por passar o id, o número do quarto e a
     * categoria quando o usuário clica em hospedar no bloco componente na tela
     * principal. Estes dados serão necessário para exisbir essas informações
     * assim que a tela for instanciada.
     */
    private void realizarReserva() {
        principal.exibirPainelCadastrarCliente(
                component.getReserva().getQuarto().getIdQuarto(),
                component.getReserva().getQuarto().getNumeroQuarto(),
                component.getReserva().getQuarto().getCategoria().getIdCategoriaQuarto()
        );
    }

    /**
     * Este método passa por parâmetro o id da reserva que está no bloco
     * componente para que sejam exibidas mais informações daquela reserva.
     */
    private void exibirInformacaoDeReserva() {
        principal.exibirPainelDadosReserva(component.getReserva().getIdReserva());
    }

    /**
     * Este método é responsável por alterar o status do quarto quando o usuário
     * clica em limpar quarto. Ao clicar no botão limpar quarto, realizamos uma
     * alteração no dado para indicar que o quarto não está sujo (sujo = 0) e
     * alteramos a tabela reserva para status = 0 para indicar que a reserva não
     * está mais ativa.
     *
     */
    private void limparQuarto() {
        System.out.println("limpar quarto " + component.getReserva().getQuarto().getIdQuarto());
        try {
            quartoDAO.removerManutencaoQuarto(component.getReserva().getQuarto().getIdQuarto());
            reservaDAO.alterarStatusReserva(component.getReserva().getIdReserva());
            principal.exibirContainerQuarto();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.BlocoComponentController.limparQuarto " + e);
        }
    }

}
