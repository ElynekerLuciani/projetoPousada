package model;

import java.math.BigDecimal;

/**
 *
 * @author Elyneker Luciani
 */
public class ContabilidadeCaixa {

    private BigDecimal valorTotalEntradas;
    private BigDecimal valorTotalSaidas;
    private BigDecimal valorTotal;

    public ContabilidadeCaixa(BigDecimal valorTotalEntradas, BigDecimal valorTotalSaidas, BigDecimal valorTotal) {
        this.valorTotalEntradas = valorTotalEntradas;
        this.valorTotalSaidas = valorTotalSaidas;
        this.valorTotal = valorTotal;
    }

    public ContabilidadeCaixa() {
        super();
    }

    public BigDecimal getValorTotalEntradas() {
        return valorTotalEntradas;
    }

    public void setValorTotalEntradas(BigDecimal valorTotalEntradas) {
        this.valorTotalEntradas = valorTotalEntradas;
    }

    public BigDecimal getValorTotalSaidas() {
        return valorTotalSaidas;
    }

    public void setValorTotalSaidas(BigDecimal valorTotalSaidas) {
        this.valorTotalSaidas = valorTotalSaidas;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
