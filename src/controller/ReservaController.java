package controller;

import componentes.Cbx_QuantidadeHospede;
import dao.CategoriaQuartoDAO;
import dao.ClienteDAO;
import dao.QuartoDAO;
import dao.ReservaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import model.Reserva;
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

    public void executarReserva(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
            case "Encerrar":
                encerrarReserva();
                break;
        }
    }

    public void executarKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10:
                pesquisar();
                break;
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
                resultado = clienteDAO.buscarNomeDocumentoCliente(pesquisar);
               telaInformacao.getjTableResultadoPesquisa().setModel(new TableModelPesquisaCliente(resultado));
            } else {
                System.out.println("erro");
            }
        } catch (Exception e) {
            System.out.println("ReservaController.pesquisar: " + e);
        }
    }

    public void setTelaInformacao(TelaReservaQuarto t) {
        this.telaInformacao = t;
    }

    public void setTelaDadosReserva(TelaDadosReserva d) {
        this.telaDadosReserva = d;
    }

    /**
     * Este método é responsável por criar uma nova reserva. Utilizando a hora
     * local para definir o momento de entrada, pega os dados de previsão de
     * saída, quantidade de pessoas e dados do cliente para criar uma nova
     * reserva.
     */
    private void realizarReserva() {
        try {
            //Obtém a hora atual para servir como data de entrada na reserva
            LocalDateTime horaDataAtual = LocalDateTime.now();
            //Pega a data informada no calendário da tela
            DateTime previsaoSaida = new DateTime(telaInformacao.getjCalendarPrevisaoSaida().getDate());
            //Atribui o horário atual para a data informada no calendário
            LocalDate dataPrevisao = LocalDate.of(previsaoSaida.getYear(), previsaoSaida.getMonthOfYear(), previsaoSaida.getDayOfMonth());
            LocalDateTime ps = dataPrevisao.atTime(horaDataAtual.toLocalTime());
            /**
             * Pega a data atual para informar a data de entrada da nova
             * reserva, a data informada no calendário para informar uma data de
             * previsão de saída e pega o número do quarto de uma variável que é
             * salva ao carregar a tela de informação do quarto.
             */
            novaReserva.setDataEntrada(horaDataAtual);
            novaReserva.setPrevisaoSaida(ps);
            novaReserva.getQuarto().setIdQuarto(TelaReservaQuarto.getIdQuarto());
            novaReserva.getQuarto().setNumeroQuarto(TelaReservaQuarto.getNumeroQuarto());
            novaReserva.getQuarto().getCategoria().setQntHospedes((int) telaInformacao.getjComboBoxQntPessoa().getSelectedItem());
            //passa por parâmetro o objeto para criar uma nova reserva
            reservaDAO.realizarReserva(novaReserva);

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //System.out.println("Data de Entrada: " + dataEntrada.format(formatter));
            //String formatado = dataEntrada.format(formatter);
            //System.out.println("DATA:: " + formatado);
            //Obtém a data da previsao de saída e a hora atual como referência para a saída
//            DateTime previsaoSaida = new DateTime(telaInformacao.getjCalendarPrevisaoSaida().getDate());
//            novaReserva.setPrevisaoSaida(previsaoSaida);
//            novaReserva.getNumeroQuarto().setNumeroQuarto(TelaReservaQuarto.getNumeroQuarto());
            // LocalDateTime local = LocalDateTime.of(previsaoSaida.getYear(), previsaoSaida.getMonthOfYear(), previsaoSaida.getDayOfMonth(), 15, 00);
            //Pega a previsão de saída do calendário com a hora atual como referência
//            SimpleDateFormat previsaoSaida = new SimpleDateFormat("YYY-MM-dd HH:mm:ss");
//            System.out.println("Previsão de Saída: " + previsaoSaida.format(telaInformacao.getjCalendarPrevisaoSaida().getDate()));
//            String d = dataEntrada.format(formatter);
//            System.out.println(d);
            // AQUI PEGA A QNT DE PESSOAS NO QUARTO
            System.out.println("qnt: " + telaInformacao.getjComboBoxQntPessoa().getSelectedItem());

            System.out.println("Dia e Hora de previsão: " + telaInformacao.getjCalendarPrevisaoSaida().getDate());
            //cbxQuantidadeHospede = new Cbx_QuantidadeHospede();

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            //verifica a diferença de dias entre a data atual e a data de entrada
            int diferencaEmDias = (int) ChronoUnit.DAYS.between(reservaModel.getDataEntrada(), dataAtual);
            System.out.println(diferencaEmDias + " diferença");

            //Se a diferença de dia é zero então conta apenas uma diária
            //Se não, é contado a quantidade de dias + 1 para calcular com o primeiro dia de hospedagem
            if (diferencaEmDias < 1) {
                telaDadosReserva.getjLabelTotalDias().setText("1 Dia");
            } else {
                telaDadosReserva.getjLabelTotalDias().setText(String.valueOf(diferencaEmDias + 1) + " Dias");
            }
            //preencher os dados de valor da reserva
            telaDadosReserva.getjLabelValorDiaria().setText(String.valueOf(reservaModel.getQuarto().getCategoria().getPrecoCategoria()));
            //preenche os dados com a quantidade de pessoas no quarto
            telaDadosReserva.getjLabelQntHospedes().setText(String.valueOf(reservaModel.getQuarto().getCategoria().getQntHospedes()));

            Duration diferenca = Duration.between(dataAtual, reservaModel.getDataEntrada());
            System.out.println(diferenca + "esta diferença");

            //Atribuir as informações nos jLabel na TelaDadosReserva
            telaDadosReserva.getjLabelInformacao().setText("Informação do Quarto: " + reservaModel.getQuarto().getNumeroQuarto());
            telaDadosReserva.getjLabelDiaEntrada().setText(reservaModel.getDataEntrada().format(formatter));

            try {
                //;
            } catch (Exception e) {
                System.out.println(e);
            }

            //telaDadosReserva.getjLabelHoraEntrada().setText(reservaModel.getDataEntrada().toLocalTime().toString());
//            System.out.println("id: " + );
//            System.out.println("numero: " + reservaModel.getNumeroQuarto().getNumeroQuarto());
//            System.out.println("data entrada: " + reservaModel.getDataEntrada());
//            System.out.println("previsao saida: " + reservaModel.getPrevisaoSaida());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("controller.ReservaController.buscarDadosReserva: " + e);
        }

    }

    private double valorReserva(int idQuarto, int qntHospedes) {
        double valorReserva = 0.0;
        try {

        } catch (Exception e) {
        }

        return valorReserva;
    }

    private void encerrarReserva() {
        try {
            System.out.println("Encerrar hospedagem do idReserva " + reservaModel.getIdReserva());
            System.out.println("Encerrar hospedagem do idQuarto " + reservaModel.getQuarto().getIdQuarto());
            LocalDateTime horaDataAtual = LocalDateTime.now();
            reservaModel.setDataSaida(horaDataAtual);
            reservaDAO.encerrarReserva(reservaModel);
            //aqui precisa colocar quarto em manutenção
            quartoDAO.colocarQuartoEmManutenção(reservaModel.getQuarto().getIdQuarto());
            principal.exibirContainerQuarto();
        } catch (Exception e) {
            System.out.println("controller.ReservaController.encerrarReserva: " + e);
        }
    }

    private void pesquisarCliente() {
        try {
            telaInformacao.getjTableResultadoPesquisa().getColumn(0).setWidth(40);
            telaInformacao.getjTableResultadoPesquisa().getColumn(1).setWidth(150);
            telaInformacao.getjTableResultadoPesquisa().getColumn(2).setWidth(60);
        } catch (Exception e) {
            System.out.println("Controller.reservaController.pesquisarCliente: " + e);
        }
    }

}
