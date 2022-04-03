/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import controller.TelaCaixaFinanceiroController;
import controller.TelaPesquisaFinanceiroController;
import java.time.Instant;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import model.TabelaModeloCaixaFinanceiro;
import model.TipoMovimentacaoComboBoxModel;

/**
 *
 * @author elyne
 */
public class TelaPesquisaCaixa extends javax.swing.JPanel {
    
    private TelaPesquisaFinanceiroController telaPesquisaFinanceiroController;
    private TabelaModeloCaixaFinanceiro tabelaCaixaModelo = new TabelaModeloCaixaFinanceiro();
    private TipoMovimentacaoComboBoxModel movimentacaoModelo;

    /**
     * Creates new form TelaPesquisaCaixa
     */
    public TelaPesquisaCaixa() {
        initComponents();
        telaPesquisaFinanceiroController = new TelaPesquisaFinanceiroController(this);
        this.jCalendarDataFinal.setMaxSelectableDate(Date.from(Instant.now()));
        this.jCalendarDataInicial.setMaxSelectableDate(Date.from(Instant.now()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCalendarDataInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jCalendarDataFinal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCaixa = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabelTotalEntradas = new javax.swing.JLabel();
        jLabelTotalSaidas = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelSaldoTotal = new javax.swing.JLabel();
        btnPesquisarCaixa = new rsbuttom.RSButtonSub();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Caixa Financeiro");

        jCalendarDataInicial.setDateFormatString("dd-MM-yyyy");
        jCalendarDataInicial.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Data Inicial");

        jCalendarDataFinal.setDateFormatString("dd-MM-yyyy");
        jCalendarDataFinal.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Data Final");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("Registro de Todas as Movimentações Financeiras:");

        jTableCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data Lançamento", "Tipo Movimentação", "Descrição", "Número do Recibo", "Valor"
            }
        ));
        jScrollPane1.setViewportView(jTableCaixa);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Valor Total das Entradas: R$");

        jLabelTotalEntradas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelTotalEntradas.setText("0.00");

        jLabelTotalSaidas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelTotalSaidas.setText("0.00");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Valor Total das Saídas: R$");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Saldo Total: R$");

        jLabelSaldoTotal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelSaldoTotal.setText("0.00");

        btnPesquisarCaixa.setText("Pesquisar");
        btnPesquisarCaixa.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnPesquisarCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCalendarDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jCalendarDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnPesquisarCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTotalEntradas)
                                    .addComponent(jLabelSaldoTotal)
                                    .addComponent(jLabelTotalSaidas))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendarDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCalendarDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisarCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelTotalEntradas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelTotalSaidas))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelSaldoTotal))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCaixaActionPerformed
       telaPesquisaFinanceiroController.executa(evt);
    }//GEN-LAST:event_btnPesquisarCaixaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonSub btnPesquisarCaixa;
    private com.toedter.calendar.JDateChooser jCalendarDataFinal;
    private com.toedter.calendar.JDateChooser jCalendarDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSaldoTotal;
    private javax.swing.JLabel jLabelTotalEntradas;
    private javax.swing.JLabel jLabelTotalSaidas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableCaixa;
    // End of variables declaration//GEN-END:variables

    public TabelaModeloCaixaFinanceiro getTabelaCaixaModelo() {
        return tabelaCaixaModelo;
    }

    public JTable getjTableCaixa() {
        return jTableCaixa;
    }
    
     public JLabel getjLabelSaldoTotal() {
        return jLabelSaldoTotal;
    }

    public JLabel getjLabelTotalEntradas() {
        return jLabelTotalEntradas;
    }

    public JLabel getjLabelTotalSaidas() {
        return jLabelTotalSaidas;
    }

    public JDateChooser getjCalendarDataFinal() {
        return jCalendarDataFinal;
    }

    public JDateChooser getjCalendarDataInicial() {
        return jCalendarDataInicial;
    }

}
