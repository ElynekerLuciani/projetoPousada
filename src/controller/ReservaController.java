package controller;

import componentes.Cbx_QuantidadeHospede;
import dao.QuartoDAO;
import dao.ReservaDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import model.Reserva;
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
    
    
    
    public void executarReserva(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
            case "Encerrar":
                encerrarReserva();
        }
    }
    
    public void setTelaInformacao(TelaReservaQuarto t) {
        this.telaInformacao = t;
    }
    
    public void setTelaDadosReserva(TelaDadosReserva d) {
        this.telaDadosReserva = d;
    }
    
    
    private void realizarReserva() {
        try {
            //Obtém a hora atual para servir como data de entrada na reserva
            LocalDateTime horaDataAtual = LocalDateTime.now();
            //Pega a data informada no calendário da tela
            DateTime previsaoSaida = new DateTime(telaInformacao.getjCalendarPrevisaoSaida().getDate());
            //Atribui o horário atual para a data informada no calendário
            LocalDate dataPrevisao =  LocalDate.of(previsaoSaida.getYear(), previsaoSaida.getMonthOfYear(), previsaoSaida.getDayOfMonth());
            LocalDateTime ps = dataPrevisao.atTime(horaDataAtual.toLocalTime());
            /**
             * Pega a data atual para informar a data de entrada da nova
             * reserva, a data informada no calendário para informar uma data
             * de previsão de saída e pega o número do quarto de uma variável
             * que é salva ao carregar a tela de informação do quarto.
             */
            novaReserva.setDataEntrada(horaDataAtual);
            novaReserva.setPrevisaoSaida(ps);
            novaReserva.getNumeroQuarto().setNumeroQuarto(TelaReservaQuarto.getNumeroQuarto());
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
    
    public void buscarDadosDaReserva(int numeroQuarto) {
        try {
            reservaModel = reservaDAO.buscarDadosReserva(numeroQuarto);
           
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            telaDadosReserva.getjLabelDiaEntrada().setText(reservaModel.getDataEntrada().format(formatter));
            
          
            //telaDadosReserva.getjLabelHoraEntrada().setText(reservaModel.getDataEntrada().toLocalTime().toString());
            
//            System.out.println("id: " + );
//            System.out.println("numero: " + reservaModel.getNumeroQuarto().getNumeroQuarto());
//            System.out.println("data entrada: " + reservaModel.getDataEntrada());
//            System.out.println("previsao saida: " + reservaModel.getPrevisaoSaida());
     
        } catch (Exception e) {
            System.out.println("controller.ReservaController.buscarDadosReserva: " + e);
        }
       
    }
    
    private void encerrarReserva() {
        try {
            System.out.println("Encerrar hospedagem do idReserva " + reservaModel.getIdReserva());
            System.out.println("Encerrar hospedagem do idQuarto " + reservaModel.getNumeroQuarto().getIdQuarto());
            LocalDateTime horaDataAtual = LocalDateTime.now();
            reservaModel.setDataSaida(horaDataAtual);
            reservaDAO.encerrarReserva(reservaModel);
            //aqui precisa colocar quarto em manutenção
            quartoDAO.colocarQuartoEmManutenção(reservaModel.getNumeroQuarto().getIdQuarto());
            principal.exibirContainerQuarto();
        } catch (Exception e) {
            System.out.println("controller.ReservaController.encerrarReserva: " + e);
        }
    }

    
}
