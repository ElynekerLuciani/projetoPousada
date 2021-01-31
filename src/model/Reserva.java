package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private Cliente cliente = new Cliente();
    private ArrayList<Pedido> pedido = new ArrayList<>();

    public Reserva() {
        super();
    }

    public Reserva(Quarto quarto, LocalDateTime entrada, LocalDateTime previsaoSaida,
            LocalDateTime saida, Cliente cliente, ArrayList<Pedido> pedidos) {
        this.quarto = quarto;
        this.dataEntrada = entrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = saida;
        this.cliente = cliente;
        this.pedido = pedidos;
    }

    public Reserva(int idReserva, Quarto numeroQuarto, LocalDateTime dataEntrada,
            LocalDateTime previsaoSaida, LocalDateTime dataSaida, Cliente cliente,
            ArrayList<Pedido> pedidos) {
        this.idReserva = idReserva;
        this.quarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.previsaoSaida = previsaoSaida;
        this.dataSaida = dataSaida;
        this.cliente = cliente;
        this.pedido = pedidos;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }

}
