/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CaixaFinanceiroDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CaixaFinanceiro;
import model.ContabilidadeCaixa;
import model.TabelaModeloCaixaFinanceiro;
import model.TipoMovimentacao;
import view.TelaCaixaFinanceiro;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaCaixaFinanceiroController {
    private TelaCaixaFinanceiro telaCaixaFinanceiro;
    private CaixaFinanceiroDAO caixaFinanceiroDAO = new CaixaFinanceiroDAO();

    public TelaCaixaFinanceiroController(TelaCaixaFinanceiro telaCaixaFinanceiro) {
        this.telaCaixaFinanceiro = telaCaixaFinanceiro;
    }
    
    public void executa(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "Lançar":
                inserirNovaMovimentacao();
                break;
        }
    }

    private void inserirNovaMovimentacao() {
        try {
            if(!telaCaixaFinanceiro.getjTextFieldDescricao().getText().trim().isEmpty()
                    && !telaCaixaFinanceiro.getjFormattedTextFieldValor().getText().trim().isEmpty()) {
                CaixaFinanceiro caixaFinanceiro = new CaixaFinanceiro();
                
                //Obtém a hora atual para servir como data de entrada na reserva
                LocalDateTime horaDataAtual = LocalDateTime.now();
                horaDataAtual.toLocalTime();
           
                caixaFinanceiro.setDataProcessamento(horaDataAtual);
                caixaFinanceiro.setTipoMovimentacao(new TipoMovimentacao(
                        telaCaixaFinanceiro.getMovimentacaoModelo().getSelectedItemCod(), ""));
                caixaFinanceiro.setDescricao(telaCaixaFinanceiro.getjTextFieldDescricao().getText().trim());
                caixaFinanceiro.setIdRecibo(Integer.parseInt(telaCaixaFinanceiro.getjTextFieldNumeroRecibo().getText().trim()));
                BigDecimal valor = new BigDecimal(telaCaixaFinanceiro.getjFormattedTextFieldValor().getText()
                        .replace(" ", "")
                        .replace(".", "")
                        .replace(",", "."));
                System.out.println(valor);
                caixaFinanceiro.setValorTotal(valor);
                
                caixaFinanceiroDAO.inserirNovaMovimentacao(caixaFinanceiro);
                JOptionPane.showMessageDialog(null, "Realizado novo lançamento financeiro.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                carregarRegistrosTabela();
                limparCampos();
            }
        } catch (Exception e) {
            System.out.println("TelaCaixaFinanceiroController.inserirNovaMovimentacao: " + e);
        }
    }
    
    public void carregarRegistrosTabela() {
        try {
            ArrayList<String[]> registros = caixaFinanceiroDAO.buscarRegistrosDoCaixa();
            if(!registros.isEmpty()) {
                telaCaixaFinanceiro.getjTableCaixa().setModel(new TabelaModeloCaixaFinanceiro(registros));
                realizarSoma();
            } else {
                JOptionPane.showMessageDialog(null, "Não Existem Registros Financeiros", "Pesquisa não realizada!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("TelaCaixaFinanceiroController.carregarRegistrosTabela: " + e);
        }
    }
    
    public ArrayList<Object> carregarTiposMovimentacao() {
        ArrayList<Object> tiposMovimentacao = new ArrayList<>();
        try {
            ArrayList<TipoMovimentacao> listaMovimentacao = caixaFinanceiroDAO.listarTiposMovimentacao();
            listaMovimentacao.forEach(tipos -> tiposMovimentacao.add((TipoMovimentacao) tipos));
        } catch (Exception e) {
            System.out.println("TelaCaixaFinanceiroController.carregarTiposMovimentacao: " + e);
        }
        return tiposMovimentacao;
    }
    
    public void realizarSoma() {
        try {
            ContabilidadeCaixa contabilidade = caixaFinanceiroDAO.realizarSomaTotal();
            telaCaixaFinanceiro.getjLabelTotalEntradas().setText(contabilidade.getValorTotalEntradas().toString());
            telaCaixaFinanceiro.getjLabelTotalSaidas().setText(contabilidade.getValorTotalSaidas().toString());
            telaCaixaFinanceiro.getjLabelSaldoTotal().setText(contabilidade.getValorTotal().toString());
        } catch (Exception e) {
            System.out.println("TelaCaixaFinanceiroController.realizarSoma: " + e);
        }
    }
    

    private void limparCampos() {
        telaCaixaFinanceiro.getjTextFieldDescricao().setText("");
        telaCaixaFinanceiro.getjTextFieldNumeroRecibo().setText("");
        telaCaixaFinanceiro.getjFormattedTextFieldValor().setValue(null);
    }
    
}
