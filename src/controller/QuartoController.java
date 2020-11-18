package controller;

import componentes.Cbx_QuantidadeHospede;
import container.ContainerBloco;
import dao.QuartoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void setTelaInformacao(TelaReservaQuarto t) {
        this.telaInformacao = t;
    }

    /**
     * Este método é responsável por buscar a quantidade de pessoas que podem
     * ficar hospedadas em um quarto. Utilizando o id da categoria do quarto, o
     * método preenche um combobox com uma lista de quantidade de pessoas que
     * podem ser colocadas em um quarto.
     *
     * @param idCategoriaQuarto
     */
    public void buscarQuantidadePessoaPorQuarto(int idCategoriaQuarto) {
        try {
            qntQuarto = new Cbx_QuantidadeHospede();
            //busca a quantidade de pessoas do quarto e coloca o retorno em uma lista
            ArrayList<CategoriaQuarto> qnt = quartoDAO.buscarQntPessoasPorQuarto(idCategoriaQuarto);
            ArrayList<Object> qntPessoas = new ArrayList();
            //Se a lista com a quantidade de pessoas por quarto não for vazia
            if (!qnt.isEmpty()) {
                for (int i = 0; i < qnt.size(); i++) {
                    qntPessoas.add(qnt.get(i).getQntHospedes());
                }
                qntQuarto = new Cbx_QuantidadeHospede(qntPessoas);
            } else {
                qntQuarto = new Cbx_QuantidadeHospede();
                System.out.println("erro no else buscarQuantidadePessoaPorQuarto");
            }
            telaInformacao.getjComboBoxQntPessoa().setModel(qntQuarto);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: controller.QuartoController.buscarQuantidadePessoaPorQuarto() " + e);
        }
    }

    /**
     * Método que busca o nome da categoria do quarto usando o id da categoria e
     * retorna para ser informada na TelaReservaQuarto. Passo a passo: Este
     * método é chamado dentro do construtor da TelaReservaQuarto, que ao ser
     * instanciada necessita exibir o nome da categoria do quarto.
     *
     * @param idCategoriaQuarto
     * @return
     */
    public String buscarCategoriaQuarto(int idCategoriaQuarto) {
        try {
            categoriaDoQuarto = quartoDAO.buscarNomeCategoriaQuarto(idCategoriaQuarto);
            if (!categoriaDoQuarto.isEmpty()) {
                return categoriaDoQuarto;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return "Não existe uma categoria definida para esse quarto";
    }

}
