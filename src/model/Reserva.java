package model;

import org.joda.time.DateTime;

/**
 *
 * @author Elyneker Luciani
 */
public class Reserva {
    
    private int idReserva;
    private Quarto numeroQuarto;
    private DateTime dataEntrada;
    private DateTime previsaoSaida;
    private DateTime dataSaida;
    
    public Reserva() {
        super();
    }
    
    public Reserva(Quarto quarto, DateTime entrada, DateTime previsaoSaida, DateTime saida) {
        this.numeroQuarto = quarto;
        this.dataEntrada = entrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = saida;
    }

    public Reserva(int idReserva, Quarto numeroQuarto, DateTime dataEntrada, DateTime previsaoSaida, DateTime dataSaida) {
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

    public DateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(DateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public DateTime getPrevisaoSaida() {
        return previsaoSaida;
    }

    public void setPrevisaoSaida(DateTime previsaoSaida) {
        this.previsaoSaida = previsaoSaida;
    }

    public DateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(DateTime dataSaida) {
        this.dataSaida = dataSaida;
    }
    
    
}
