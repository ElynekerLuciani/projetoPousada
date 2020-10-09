package model;

import java.time.LocalDateTime;

/**
 *
 * @author Elyneker Luciani
 */
public class Reserva {
    
    private int idReserva;
    private Quarto quarto = new Quarto();
    private LocalDateTime dataEntrada;
    private LocalDateTime previsaoSaida;
    private LocalDateTime dataSaida;
    
    public Reserva() {
        super();
    }
    
    public Reserva(Quarto quarto, LocalDateTime entrada, LocalDateTime previsaoSaida, LocalDateTime saida) {
        this.quarto = quarto;
        this.dataEntrada = entrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = saida;
    }

    public Reserva(int idReserva, Quarto numeroQuarto, LocalDateTime dataEntrada, LocalDateTime previsaoSaida, LocalDateTime dataSaida) {
        this.idReserva = idReserva;
        this.quarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = dataSaida;
    }
    

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getPrevisaoSaida() {
        return previsaoSaida;
    }

    public void setPrevisaoSaida(LocalDateTime previsaoSaida) {
        this.previsaoSaida = previsaoSaida;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }
    
    
}
