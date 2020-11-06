package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Elyneker Luciani
 */
public class CaixaFinanceiro {
    private int idCaixa = 0;
    private LocalDateTime dataProcessamento;
    private TipoMovimentacao tipoMovimentacao;
    private String descricao;
    private int idRecibo=0;
    private BigDecimal valorTotal;
    
    public CaixaFinanceiro() {
        super();
    }

    public CaixaFinanceiro(LocalDateTime dataProcessamento, TipoMovimentacao tipoMovimentacao, String descricao, int idRecibo, BigDecimal valorTotal) {
        this.dataProcessamento = dataProcessamento;
        this.tipoMovimentacao = tipoMovimentacao;
        this.descricao = descricao;
        this.idRecibo = idRecibo;
        this.valorTotal = valorTotal;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }
    
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
       
}
