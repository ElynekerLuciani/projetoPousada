package controller;

import componentes.BlocoComponent;
import container.ContainerBloco;
import dao.ReservaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Reserva;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerBlocoController {

    private final ContainerBloco containerBloco;
    private final ReservaDAO reservaDAO = new ReservaDAO();

    //construtor da classe container bloco controller
    public ContainerBlocoController(ContainerBloco containerQuarto) {
        this.containerBloco = containerQuarto;
    }

    /**
     * Neste método instanciamos e atribuímos aos blocos components um dado de
     * reserva para cada bloco que representa um quarto. Para cada bloco
     * component atribuímos uma informação da reserva que está ocupando um
     * quarto. Estes dados serão importantes para quando o usuário clicar no
     * botão de hospedar, informações ou limpar. Caso não exista quartos
     * cadastrados, é utilizado um construtor em BlocoComponent que informará
     * que não existem quartos cadastrados.
     */
    public void exibirBlocoComponente() {
        try {
            //Pesquisa se dados das reservas ativas
            ArrayList<Reserva> lista = reservaDAO.buscarReservarAtivasParaBlocos();
            //Se existir quartos cadastrados preenchendo a lista de reservas
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    //Atribui a cada bloco uma informação de reserva
                    BlocoComponent blocoComponente = new BlocoComponent(lista.get(i));
                    //instanciar na tela principal
                    exibirJPanel(blocoComponente);
                }
            } else {
                //Inicializa utilizando um construtor para quartos não
                //cadastrados
                BlocoComponent blocoComponente = new BlocoComponent();
                //instanciar na tela principal
                exibirJPanel(blocoComponente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.ContainerBlocoController.exibirBlocoComponente()" + e);
        }
    }

    /**
     * Neste método instanciamos os blocos na tela principal utilizando o
     * ContainerBloco.
     */
    private void exibirJPanel(JPanel jPanel) {
        containerBloco.getjPanelCentro().add(jPanel);
        containerBloco.getjPanelCentro().revalidate();
        containerBloco.getjPanelCentro().repaint();
    }

}
