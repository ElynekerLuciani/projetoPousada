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
    private BigDecimal valorTotal;
    
    public CaixaFinanceiro() {
        super();
    }

    public CaixaFinanceiro(LocalDateTime dataProcessamento, TipoMovimentacao tipoMovimentacao, String descricao, BigDecimal valorTotal) {
        this.dataProcessamento = dataProcessamento;
        this.tipoMovimentacao = tipoMovimentacao;
        this.descricao = descricao;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
       
}
