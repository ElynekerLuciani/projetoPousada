package controller;

import componentes.Cbx_QuantidadeHospede;
import dao.CategoriaQuartoDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import dao.QuartoDAO;
import dao.ReservaDAO;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.Calcular;
import model.CategoriaProduto;
import model.Pedido;
import model.Produto;
import model.Reserva;
import model.TableModelPedidos;
import model.TableModelPesquisaCliente;
import org.joda.time.DateTime;
import view.TelaDadosReserva;
import view.TelaReservaQuarto;

/**
 *
 * @author Elyneker Luciani
 */
public class ReservaController {
    
    private TelaReservaQuarto telaInformacao;
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private Cbx_QuantidadeHospede cbxQuantidadeHospede;
    private final Reserva novaReserva = new Reserva();
    private TelaDadosReserva telaDadosReserva;
    private Reserva reservaModel = new Reserva();
    private final PrincipalController principal = PrincipalController.getInstancia();
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private final CategoriaQuartoDAO categoriaQuartoDAO = new CategoriaQuartoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final Calcular calcular = new Calcular();
    private final Pedido pedido = new Pedido();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    
    private BigDecimal valorConsumido = new BigDecimal("0.00");
    private BigDecimal valorTotalDiarias = new BigDecimal("0.00");
    private BigDecimal valorTotalDaHospedagemSemDesconto = new BigDecimal("0.00");
    private BigDecimal valorTotalPagar = new BigDecimal("0.00");
    
    public void setTelaInformacao(TelaReservaQuarto t) {
        this.telaInformacao = t;
    }
    
    public void setTelaDadosReserva(TelaDadosReserva d) {
        this.telaDadosReserva = d;
    }
    
    public void executarReserva(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
            case "Encerrar":
                encerrarReserva();
                break;
            case "comboBoxChanged":
                buscarProdutoCombobox();
                break;
            case "Adicionar":
                adicionarProdutoConsumido();
                break;
            case "Remover":
                removerProdutoConsumido();
                break;
        }
        
    }
    
    public void executarKeyEvent(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ENTER:
                pesquisar();
                break;
        }
    }
    
    public void executaMouseClicked(MouseEvent evt) {
        if (telaInformacao.getjTableResultadoPesquisa().getSelectedRow() != -1) {
            int linha = telaInformacao.getjTableResultadoPesquisa().getSelectedRow();
            telaInformacao.getjLabelNomeCliente().setText((String) telaInformacao.getjTableResultadoPesquisa().getModel().getValueAt(linha, 1));
        }
    }
    
    private void pesquisar() {
        String pesquisar = telaInformacao.getjTextFieldPesquisar().getText()
                .trim().replace(".", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "")
                .replace(".", "");
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            if (!pesquisar.isEmpty()) {
                resultado = clienteDAO.buscarNomeOuDocumentoCliente(pesquisar);
                if (!resultado.isEmpty()) {
                    telaInformacao.getjTableResultadoPesquisa().setModel(
                            new TableModelPesquisaCliente(resultado));
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Informe o nome ou documento do cliente para pesquisar",
                        "Reserva não realizada!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("ReservaController.pesquisar: " + e);
        }
    }

    /**
     * Este método é responsável por criar uma nova reserva. Utilizando a hora
     * local para definir o momento de entrada, pega os dados de previsão de
     * saída, quantidade de pessoas e dados do cliente para criar uma nova
     * reserva.
     */
    private void realizarReserva() {
        try {
            if (telaInformacao.getjTableResultadoPesquisa().getSelectedRow() != -1
                    && telaInformacao.getjCalendarPrevisaoSaida().getDate() != null) {
                int linha = telaInformacao.getjTableResultadoPesquisa().getSelectedRow();
                System.out.println(telaInformacao.getjTableResultadoPesquisa().getModel().getValueAt(linha, 0));

                //Obtém a hora atual para servir como data de entrada na reserva
                LocalDateTime horaDataAtual = LocalDateTime.now();
                //Pega a data informada no calendário da tela
                DateTime previsaoSaida = new DateTime(telaInformacao.getjCalendarPrevisaoSaida().getDate());
                //Atribui o horário atual para a data informada no calendário
                LocalDate dataPrevisao = LocalDate.of(
                        previsaoSaida.getYear(),
                        previsaoSaida.getMonthOfYear(),
                        previsaoSaida.getDayOfMonth());
                LocalDateTime ps = dataPrevisao.atTime(horaDataAtual.toLocalTime());

                /**
                 * Pega a data atual para informar a data de entrada da nova
                 * reserva, a data informada no calendário para informar uma
                 * data de previsão de saída e pega o número do quarto de uma
                 * variável que é salva ao carregar a tela de informação do
                 * quarto.
                 */
                novaReserva.setDataEntrada(horaDataAtual);
                novaReserva.setPrevisaoSaida(ps);
                novaReserva.getQuarto().setIdQuarto(TelaReservaQuarto.getIdQuarto());
                novaReserva.getQuarto().setNumeroQuarto(TelaReservaQuarto.getNumeroQuarto());
                novaReserva.getQuarto().getCategoria().setQntHospedes((int) telaInformacao.getjComboBoxQntPessoa().getSelectedItem());
                novaReserva.getCliente().setIdCliente((int) telaInformacao.getjTableResultadoPesquisa().getModel().getValueAt(linha, 0));

                //passa por parâmetro o objeto para criar uma nova reserva
                reservaDAO.realizarReserva(novaReserva);
                int idReserva = reservaDAO.buscarIdReserva(TelaReservaQuarto.getNumeroQuarto());
                principal.exibirPainelDadosReserva(idReserva);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o cliente e defina a data prevista de saída", "Reserva não realizada!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("realizar Reserva" + e);
        }
    }

    /**
     * Este método é chamado no construtor da TelaDadosReserva para buscar todas
     * as informações da reserva que foi selecionado no bloco componente na tela
     * principal. Buscamos as informações a partir do número do id da reserva do
     * quarto.
     *
     * @param idReserva
     */
    public void buscarDadosDaReserva(int idReserva) {
        try {
            //Busca os dados da reserva através do id
            reservaModel = reservaDAO.buscarDadosReserva(idReserva);
            //Pega a hora atual do sistema
            LocalDateTime dataAtual = LocalDateTime.now();
            //Cria um formato agradável de horas para exibir ao usuário
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            //verifica a diferença de dias entre a data atual e a data de entrada
            int diferencaEmDias = (int) ChronoUnit.DAYS.between(reservaModel.getDataEntrada(), dataAtual);
            //Se a diferença de dia é zero então conta apenas uma diária
            //Se não, é contado a quantidade de dias + 1 para calcular com o primeiro dia de hospedagem
            if (diferencaEmDias < 1) {
                telaDadosReserva.getjLabelTotalDias().setText("1 Dia");
                diferencaEmDias = 1;
            } else {
                diferencaEmDias = diferencaEmDias + 1;
                telaDadosReserva.getjLabelTotalDias().setText(String.valueOf(diferencaEmDias) + " Dias");
            }
            //preencher os dados de valor da reserva
            telaDadosReserva.getjLabelValorDiaria().setText(String.valueOf(
                    reservaModel.getQuarto().getCategoria().getPrecoCategoria()));
            //preenche os dados com a quantidade de pessoas no quarto
            telaDadosReserva.getjLabelQntHospedes().setText(String.valueOf(
                    reservaModel.getQuarto().getCategoria().getQntHospedes()));

            //Atribuindo o valor da diária
            BigDecimal valorDaDiaria = new BigDecimal(String.valueOf(
                    reservaModel.getQuarto().getCategoria().getPrecoCategoria()));
            //Atribuindo o valor total das diarias
            valorTotalDiarias = calcular.calcularValorDasDiarias(valorDaDiaria, diferencaEmDias);

            //Atribuir as informações nos jLabel na TelaDadosReserva
            telaDadosReserva.getjLabelInformacao().setText("Informação do Quarto: " + reservaModel.getQuarto().getNumeroQuarto());
            telaDadosReserva.getjLabelDiaEntrada().setText(reservaModel.getDataEntrada().format(formatter));

            //Preenchendo as informações do cliente
            telaDadosReserva.getjLabelNomeCliente().setText("Nome do Cliente: " + reservaModel.getCliente().getNomeCliente().toUpperCase());
            telaDadosReserva.getjFormattedTextFieldCelular().setText(reservaModel.getCliente().getContatoCliente().getCelular());

            //Método responsável pelos cálculos e exibição dos dados de consumo na tela
            exibirDespesasDaDiaria();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.ReservaController.buscarDadosReserva: " + e);
        }
    }
    
    private void encerrarReserva() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja Encerrar esta hospedagem?", "Encerrar Hospedagem", dialogButton);
        if (dialogResult == 0) {
            try {
                LocalDateTime horaDataAtual = LocalDateTime.now();
                reservaModel.setDataSaida(horaDataAtual);
                reservaDAO.encerrarReserva(reservaModel);
                //aqui precisa colocar quarto em manutenção
                quartoDAO.colocarQuartoEmManutenção(reservaModel.getQuarto().getIdQuarto());
                principal.exibirContainerQuarto();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("controller.ReservaController.encerrarReserva: " + e);
            }
        }
    }

//    private void formatarTabelaPesquisarCliente() {
//        try {
//            telaInformacao.getjTableResultadoPesquisa().getColumn(0).setWidth(40);
//            telaInformacao.getjTableResultadoPesquisa().getColumn(1).setWidth(150);
//            telaInformacao.getjTableResultadoPesquisa().getColumn(2).setWidth(60);
//            telaInformacao.getjTableResultadoPesquisa().getColumn(3).setWidth(60);
//        } catch (Exception e) {
//            System.out.println("Controller.reservaController.pesquisarCliente: " + e);
//        }
//    }
    public ArrayList<Object> buscarCategoriaProdutoCombobox() {
        ArrayList<Object> categoriaProduto = new ArrayList<>();
        try {
            ArrayList<CategoriaProduto> listaDeCategorias = produtoDAO.listarCategorias();
            listaDeCategorias.forEach(cat -> categoriaProduto.add((CategoriaProduto) cat));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ReservaController.buscarCategoriaProduto: " + e);
        }
        return categoriaProduto;
    }
    
    public ArrayList<Object> buscarProdutoCombobox() {
        ArrayList<Object> produto = new ArrayList<>();
        try {
            ArrayList<Produto> listaDeProdutos = produtoDAO.listarProdutos(
                    telaDadosReserva.getCategoriaProdModelo().getSelectedItemCod());
            if (!listaDeProdutos.isEmpty()) {
                listaDeProdutos.forEach(cat -> produto.add((Produto) cat));
            } else {
                telaDadosReserva.getProdutoModelo().resert();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ReservaController.buscarProduto: " + e);
        }
        return produto;
    }
    
    public void selecionarProduto() {
        try {
            telaDadosReserva.getjLabelprecoUnit().setText(
                    String.valueOf(telaDadosReserva.getProdutoModelo().getValor()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void adicionarProdutoConsumido() {
        try {
            pedido.setIdReserva(TelaDadosReserva.getIdReservaQuarto());
            pedido.setProduto(new Produto(
                    telaDadosReserva.getProdutoModelo().getSelectedItemCod(),
                    telaDadosReserva.getProdutoModelo().getNomeProduto(),
                    new BigDecimal(String.valueOf(
                            telaDadosReserva.getProdutoModelo().getValor())),
                    new CategoriaProduto(
                            telaDadosReserva.getCategoriaProdModelo().getSelectedItemCod(), "")));
            pedido.setQuantidade(Integer.parseInt(telaDadosReserva.getjTextFieldQuantidade().getText()));
            pedidoDAO.adicionarPedido(pedido);
            carregarTabelaDeProdutosConsumidos();
            exibirDespesasDaDiaria();
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarTabelaDeProdutosConsumidos() {
        try {
            ArrayList<String[]> produtosConsumidos = pedidoDAO.buscarProdutosConsumidos(TelaDadosReserva.getIdReservaQuarto());
            if (!produtosConsumidos.isEmpty()) {
                telaDadosReserva.getjTableProdutosConsumidos().setModel(new TableModelPedidos(produtosConsumidos));
                valorConsumido = calcular.somarTotal(produtosConsumidos, 4);
            } else {
                valorConsumido = new BigDecimal("0.00");
                telaDadosReserva.getjTableProdutosConsumidos().clearSelection();
                telaDadosReserva.getjTableProdutosConsumidos().setModel(new TableModelPedidos());
                telaDadosReserva.getjTableProdutosConsumidos().removeAll();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ReservaController.carregarTabela: " + e);
        }
    }
    
    private void exibirDespesasDaDiaria() {
        try {
            //Informar o valor dos produtos consumidos (atribuido em carregar tabela de produtos consumidos
            telaDadosReserva.getjLabelValorConsumo().setText(valorConsumido.toString());
            //exibe o valor total da diária
            telaDadosReserva.getjLabelTotalDiarias().setText(valorTotalDiarias.toString());
            //soma do valor das diárias com o valor consumido (atribuido em carregar Tabela de produtos consumidos)
            valorTotalDaHospedagemSemDesconto = calcular.totalHospedagem(valorTotalDiarias, valorConsumido);
            //exibe o valor total sem o desconto 
            telaDadosReserva.getjLabelValorTotal().setText(valorTotalDaHospedagemSemDesconto.toString());
        } catch (Exception e) {
            System.out.println("ReservaController.exibirDespesas: " + e);
        }
    }
    
    public void calcularDesconto(KeyEvent e) {
        try {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                Double desconto = Double.parseDouble(telaDadosReserva.getjFormattedTextFieldPorcentagem()
                        .getText()
                        .replace(" ", "")
                        .replace(".", "")
                        .replace(",", "."));
                if (desconto >= 0.00 && desconto <= 100.00) {
                    BigDecimal porcentagem = new BigDecimal(desconto);
                    BigDecimal valorDoDesconto = valorTotalDaHospedagemSemDesconto.multiply(porcentagem).divide(new BigDecimal(100));
                    valorTotalPagar = valorTotalDaHospedagemSemDesconto.subtract(valorDoDesconto);
                    telaDadosReserva.getjLabelValorDesconto().setText(valorDoDesconto.toString());
                    telaDadosReserva.getjLabelValorAPagar().setText(valorTotalPagar.toString());
                } else {
                     JOptionPane.showMessageDialog(null, "Informe quantos porcentos deseja oferecer de desconto", "Desconto não aplicado!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        } catch (NumberFormatException ex) {
            System.out.println("calcularDescontro: " + ex);
        }
        
    }

    private void removerProdutoConsumido() {
        try {
            if(telaDadosReserva.getjTableProdutosConsumidos().getSelectedRow() != -1) {
                int linha = telaDadosReserva.getjTableProdutosConsumidos().getSelectedRow();
                int idPedido = (int) telaDadosReserva.getjTableProdutosConsumidos().getModel().getValueAt(linha, 0);
                System.out.println(idPedido);
                produtoDAO.removerProdutoConsumido(idPedido);
                carregarTabelaDeProdutosConsumidos();
                exibirDespesasDaDiaria();
            } else {
               JOptionPane.showMessageDialog(null, "Não existem produtos a serem removidos.", "Remover Produtos Consumidos", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("ReservaController.removerProdutoConsumido");
        }
        
    }
    
}
