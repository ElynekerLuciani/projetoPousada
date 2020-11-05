/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Elyneker Luciani
 */
public class ReciboHospedagem {
    private int idRecibo = 0;
    private int idReserva;
    private LocalDateTime dataProcessamento;
    private BigDecimal valorDiaria;
    private BigDecimal valorConsumido;
    private BigDecimal desconto;
    private BigDecimal valorTotalPagar;
    
    public ReciboHospedagem() {
        super();
    }

    public ReciboHospedagem(int idReserva, LocalDateTime data, BigDecimal valorDiaria, BigDecimal valorConsumido, BigDecimal desconto, BigDecimal total) {
        this.idReserva = idReserva;
        this.dataProcessamento = data;
        this.valorDiaria = valorDiaria;
        this.valorConsumido = valorConsumido;
        this.desconto = desconto;
        this.valorTotalPagar = total;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public BigDecimal getValorConsumido() {
        return valorConsumido;
    }

    public void setValorConsumido(BigDecimal valorConsumido) {
        this.valorConsumido = valorConsumido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotalPagar() {
        return valorTotalPagar;
    }

    public void setValorTotalPagar(BigDecimal valorTotalPagar) {
        this.valorTotalPagar = valorTotalPagar;
    }

}
