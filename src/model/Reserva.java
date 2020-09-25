package model;

import java.time.LocalDateTime;

/**
 *
 * @author Elyneker Luciani
 */
public class Reserva {
    
    private int idReserva;
    private Quarto numeroQuarto = new Quarto();
    private LocalDateTime dataEntrada;
    private LocalDateTime previsaoSaida;
    private LocalDateTime dataSaida;
    
    public Reserva() {
        super();
    }
    
    public Reserva(Quarto quarto, LocalDateTime entrada, LocalDateTime previsaoSaida, LocalDateTime saida) {
        this.numeroQuarto = quarto;
        this.dataEntrada = entrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = saida;
    }

    public Reserva(int idReserva, Quarto numeroQuarto, LocalDateTime dataEntrada, LocalDateTime previsaoSaida, LocalDateTime dataSaida) {
        this.idReserva = idReserva;
        this.numeroQuarto = numeroQuarto;
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

    public Quarto getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Quarto numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
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
