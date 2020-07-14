package controller;

import componentes.QuartoComponent;
import container.ContainerQuarto;
import dao.QuartoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Quarto;
import view.TelaHospedarCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoController {

    private static QuartoController controller;
    private ContainerQuarto container;
    private final QuartoDAO quartoDAO = new QuartoDAO();
    
    private String categoriaDoQuarto;

    public QuartoController() {
        super();
    }

    public static QuartoController getInstancia() {
        if (controller == null) {
            controller = new QuartoController();
        }
        return controller;
    }

    public void setQuartoController(ContainerQuarto c) {
        this.container = c;

    }

    /**
     * Método que realiza a busca dos quartos cadastrados e informa na tela
     * inicial se o quarto está livre ou ocupado.
     */
    public void exibirQuartoComponente() {
        try {
            ArrayList<Quarto> lista = quartoDAO.quartosDisponiveis();
            //Se existir quartos cadastrados
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    QuartoComponent quartoComponente = new QuartoComponent(lista.get(i));
                    exibirJPanel(quartoComponente);
                }
            } else {
                //Inicializa utilizando um construtor para quartos não
                //cadastrados
                QuartoComponent quartoComponente = new QuartoComponent();
                exibirJPanel(quartoComponente);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    public void exibirPainelTeste(Quarto q) {
        TelaHospedarCliente cadastroRapido = new TelaHospedarCliente(q.getNumeroQuarto(), q.getIdQuarto());
        exibirJPanel(cadastroRapido);
    }

    private void exibirJPanel(JPanel jPanel) {
        container.getjPanelCentro().add(jPanel);
        container.getjPanelCentro().revalidate();
        container.getjPanelCentro().repaint();
    }

    public void buscarStatusQuarto() throws SQLException, ClassNotFoundException {

//         try {
//             if(lista.isEmpty()) {
//                 for(int i = 0; i < lista.size(); i++) {
//                     System.out.println(lista.get(i).getNumeroQuarto() + lista.get(i).getStatusQuarto().toString());
//                }
//             
//                 
//             
//         }
//         } catch (Exception e) {
//         }
    }

    /**
     * Método que busca pelo o id do quarto o nome da categoria a que pertence
     * e retorna a categoria para ser informada na TelaHospedarCliente.
     * 
     * Passo a passo:
     * Este método é chamado dentro do construtor da TelaHospedarCliente, que
     * ao ser instanciada necessita exibir o nome da categoria do quarto.
     * @param idQuarto
     * @return 
     */
    public String buscarCategoriaQuarto(int idQuarto) {
        try {
            categoriaDoQuarto = quartoDAO.burcarCategoriaQuarto(idQuarto);
            if(!categoriaDoQuarto.isEmpty()) {
                return categoriaDoQuarto;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return "vazio";
        
    }

}
