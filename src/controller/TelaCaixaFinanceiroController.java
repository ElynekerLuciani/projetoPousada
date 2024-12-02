package controller;

import dao.CaixaFinanceiroDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        switch (evt.getActionCommand()) {
            case "Lançar":
                inserirNovaMovimentacao();
                break;
            case "Cancelar":
                limparCampos();
                break;
        }
    }

    private void inserirNovaMovimentacao() {
           
        try {
            if(telaCaixaFinanceiro.getjTextFieldDescricao().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe uma descrição para o lançamento", "Erro!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if(telaCaixaFinanceiro.getjFormattedTextFieldValor().getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Lançamento incorreto", "Erro!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BigDecimal saldo = new BigDecimal(telaCaixaFinanceiro.getjFormattedTextFieldValor().getText()
                    .replace(" ", "")
                    .replace(".", "")
                    .replace(",", "."));
            
            if(saldo.compareTo(BigDecimal.ZERO)<= 0){
                JOptionPane.showMessageDialog(null, "Informe um valor de lançamento", "Erro!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(telaCaixaFinanceiro.getMovimentacaoModelo().getSelectedItemCod() == 2){
                if(!possuiSaldo(saldo)){
                    JOptionPane.showMessageDialog(null, "Não possui saldo", "Erro!", JOptionPane.ERROR_MESSAGE);
                    return;
                }    
            }
             
            CaixaFinanceiro caixaFinanceiro = new CaixaFinanceiro();

            //Obtém a hora atual para servir como data de entrada na reserva
            LocalDateTime horaDataAtual = LocalDateTime.now();
            horaDataAtual.toLocalTime();

            caixaFinanceiro.setDataProcessamento(horaDataAtual);
            caixaFinanceiro.setTipoMovimentacao(new TipoMovimentacao(
                    telaCaixaFinanceiro.getMovimentacaoModelo().getSelectedItemCod(), ""));
            caixaFinanceiro.setDescricao(telaCaixaFinanceiro.getjTextFieldDescricao().getText().trim());

            caixaFinanceiro.setIdRecibo(0);
            BigDecimal valor = new BigDecimal(telaCaixaFinanceiro.getjFormattedTextFieldValor().getText()
                    .replace(" ", "")
                    .replace(".", "")
                    .replace(",", "."));
            caixaFinanceiro.setValorTotal(valor);

            caixaFinanceiroDAO.inserirNovaMovimentacao(caixaFinanceiro);
            JOptionPane.showMessageDialog(null, "Realizado novo lançamento financeiro.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            carregarRegistrosTabela();
            limparCampos();
            
        } catch (Exception e) {
            System.out.println("TelaCaixaFinanceiroController.inserirNovaMovimentacao: " + e);
            JOptionPane.showMessageDialog(null, "Erro ao realizar lançamento", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregarRegistrosTabela() throws ParseException {
        try {
            ArrayList<String[]> registros = caixaFinanceiroDAO.buscarRegistrosDoCaixa();
            if (!registros.isEmpty()) {
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
    
    private boolean possuiSaldo(BigDecimal valor) {
        try {
             ContabilidadeCaixa contabilidade = caixaFinanceiroDAO.realizarSomaTotal();
             
            if (contabilidade == null || contabilidade.getValorTotal() == null) {
            System.out.println("Erro: Valor total da contabilidade não encontrado.");
            return false;
            }
            
            BigDecimal saldo = contabilidade.getValorTotal();
             
             if(valor.compareTo(saldo) > 0){
                return false; 
             }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("TelaCaixaFinanceiroController.realizarSoma: " + e);
        }
        return true;
    }

}
