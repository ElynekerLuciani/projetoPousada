/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CaixaFinanceiroDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ContabilidadeCaixa;
import model.TabelaModeloCaixaFinanceiro;
import org.joda.time.DateTime;
import view.TelaPesquisaCaixa;

/**
 *
 * @author elyne
 */
public class TelaPesquisaFinanceiroController {
    private TelaPesquisaCaixa telaPesquisaCaixa;
    private CaixaFinanceiroDAO caixaFinanceiroDAO = new CaixaFinanceiroDAO();

    public TelaPesquisaFinanceiroController(TelaPesquisaCaixa telaPesquisaCaixa) {
        this.telaPesquisaCaixa = telaPesquisaCaixa;
    }

    public void executa(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "Pesquisar":
                pesquisarPeriodo();
                break;
        }
    }

    private void pesquisarPeriodo() {
        try {
           if(telaPesquisaCaixa.getjCalendarDataInicial().getDate() != null &&
                   telaPesquisaCaixa.getjCalendarDataFinal().getDate() != null) {

                //Pega a data informada no calendário da tela
                DateTime dataInicial = new DateTime(telaPesquisaCaixa.getjCalendarDataInicial().getDate());
                DateTime dataFinal = new DateTime(telaPesquisaCaixa.getjCalendarDataFinal().getDate());

                LocalDate periodoInicial = LocalDate.of(
                        dataInicial.getYear(),
                        dataInicial.getMonthOfYear(),
                        dataInicial.getDayOfMonth());
                LocalDateTime pInicial = LocalDateTime.of(
                         periodoInicial.getYear(),
                         periodoInicial.getMonth(),
                         periodoInicial.getDayOfMonth(), 00, 00, 00);

                 LocalDate periodoFinal = LocalDate.of(
                        dataFinal.getYear(),
                        dataFinal.getMonthOfYear(),
                        dataFinal.getDayOfMonth());
                 LocalDateTime pFinal = LocalDateTime.of(
                         periodoFinal.getYear(),
                         periodoFinal.getMonth(),
                         periodoFinal.getDayOfMonth(), 23, 59, 59);

               ArrayList<String[]> registros = caixaFinanceiroDAO.buscarRegistrosPeriodoCaixa(pInicial, pFinal);
                if (!registros.isEmpty()) {
                telaPesquisaCaixa.getjTableCaixa().setModel(new TabelaModeloCaixaFinanceiro(registros));
                realizarSoma();
            } else {
                JOptionPane.showMessageDialog(null, "Não Existem Registros Financeiros", "Pesquisa não realizada!", JOptionPane.ERROR_MESSAGE);
            }
           }
        } catch (Exception e) {
            System.out.println("controller.TelaPesquisaFinanceiroController.pesquisaPeríodo: " + e);
        }

    }

    public void realizarSoma() {
        try {
            ContabilidadeCaixa contabilidade = caixaFinanceiroDAO.realizarSomaTotal();
            telaPesquisaCaixa.getjLabelTotalEntradas().setText(contabilidade.getValorTotalEntradas().toString());
            telaPesquisaCaixa.getjLabelTotalSaidas().setText(contabilidade.getValorTotalSaidas().toString());
            telaPesquisaCaixa.getjLabelSaldoTotal().setText(contabilidade.getValorTotal().toString());
        } catch (Exception e) {
            System.out.println("TelaCaixaFinanceiroController.realizarSoma: " + e);
        }
    }



}
