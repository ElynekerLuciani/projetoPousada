/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import componentes.Cbx_QuantidadeHospede;
import dao.ReservaDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import model.Reserva;
import org.joda.time.DateTime;
import view.TelaInformacaoQuarto;

/**
 *
 * @author Elyneker Luciani
 */
public class ReservaController {
    private TelaInformacaoQuarto telaInformacao;
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private Cbx_QuantidadeHospede cbxQuantidadeHospede;
    private Reserva novaReserva = new Reserva();
    
    
    
    public void executarReserva(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
        }
    }
    
   
    
    public void setTelaInformacao(TelaInformacaoQuarto t) {
        this.telaInformacao = t;
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
            novaReserva.getNumeroQuarto().setNumeroQuarto(TelaInformacaoQuarto.getNumeroQuarto());
            reservaDAO.realizarReserva(novaReserva);
           
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //System.out.println("Data de Entrada: " + dataEntrada.format(formatter)); 
            //String formatado = dataEntrada.format(formatter);
            //System.out.println("DATA:: " + formatado);
            //Obtém a data da previsao de saída e a hora atual como referência para a saída
//            DateTime previsaoSaida = new DateTime(telaInformacao.getjCalendarPrevisaoSaida().getDate());
//            novaReserva.setPrevisaoSaida(previsaoSaida);
//            novaReserva.getNumeroQuarto().setNumeroQuarto(TelaInformacaoQuarto.getNumeroQuarto());
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

    
}
