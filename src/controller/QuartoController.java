package controller;

import componentes.Cbx_QuantidadeHospede;
import container.ContainerBloco;
import dao.QuartoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.CategoriaQuarto;
import view.TelaReservaQuarto;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoController {
    private ContainerBloco container;
    private TelaReservaQuarto telaInformacao;
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private String categoriaDoQuarto;
    private Cbx_QuantidadeHospede qntQuarto;

    public QuartoController() {
        super();
    }



//    public void setQuartoController(ContainerBloco c) {
//        this.container = c;
//    }
//    
    public void setTelaInformacao(TelaReservaQuarto t) {
        this.telaInformacao = t;
    }

    
    public void buscarQuantidadePessoaPorQuarto(int idCategoriaQuarto) {
        try {
            qntQuarto = new Cbx_QuantidadeHospede();
            ArrayList<CategoriaQuarto> qnt = quartoDAO.buscarQntPessoasPorQuarto(idCategoriaQuarto);
            ArrayList<Object> qntPessoas = new ArrayList();
            if(!qnt.isEmpty()) {
                for (int i = 0; i < qnt.size(); i++) {
                    qntPessoas.add(qnt.get(i).getQntHospedes());
                } 
            } else {
                System.out.println("erro no else");
                 
            }
        
            qntQuarto = new Cbx_QuantidadeHospede(qntPessoas);
            telaInformacao.getjComboBoxQntPessoa().setModel(qntQuarto);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: controller.QuartoController.buscarQuantidadePessoaPorQuarto() " + e );
        }
        
    }
    
    private void exibirJPanel(JPanel jPanel) {
        container.getjPanelCentro().add(jPanel);
        container.getjPanelCentro().revalidate();
        container.getjPanelCentro().repaint();
    }

    /**
     * Método que busca pelo o id do quarto o nome da categoria a que pertence
       e retorna a categoria para ser informada na TelaReservaQuarto.
     * 
     * Passo a passo:
        Este método é chamado dentro do construtor da TelaReservaQuarto, que
        ao ser instanciada necessita exibir o nome da categoria do quarto.
     * @param idCategoriaQuarto
     * @return 
     */
    public String buscarCategoriaQuarto(int idCategoriaQuarto) {
        try {
            categoriaDoQuarto = quartoDAO.buscarNomeCategoriaQuarto(idCategoriaQuarto);
            if(!categoriaDoQuarto.isEmpty()) {
                return categoriaDoQuarto;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return "vazio";
    }

//    public void exibirPesquisa() {
//        try {
//            HospedarCliente p = new HospedarCliente();
//            exibirJPanelPesquisa(p);
//        } catch (Exception e) {
//            System.out.println("controller.QuartoController.exibirPesquisa()"+ e);
//        }
//       
//        
//    }
    
//    private void exibirJPanelPesquisa(JPanel jPanel) {
//       
//        telaInformacao.getjPanelCentral().removeAll();
//        telaInformacao.getjPanelCentral().add(jPanel, BorderLayout.CENTER);
//        telaInformacao.getjPanelCentral().revalidate();
//        telaInformacao.getjPanelCentral().repaint();
//    }
    
//    public void exibirPainelCentral(boolean b) {
//         if(telaInformacao != null && b) {
//            
//            telaInformacao.setHospedarCliente(telaHospedarCliente);
//            telaInformacao.getHospedarCliente().setBounds(5, 5, 700, 420);
//            exibirJPanelPesquisa(telaInformacao.getHospedarCliente());
//         } else {
////            telaHospedarcliente.setInformarDadosHospedagem(new InformarDadosHospedagem());
////            telaHospedarcliente.getInformarDadosHospedagem().setBounds(5, 5, 700, 420);
////            exibirJPanelPesquisa(telaHospedarcliente.getInformarDadosHospedagem());
//         }
//    }
 
}
