package model;

import java.math.BigDecimal;

/**
 *
 * @author Elyneker Luciani
 */
public class Pedido {
    private int idPedido=0;
    private int idReserva;
    private Produto produto;
    private int quantidade;
    private BigDecimal preco;

    public Pedido(int idPedido, int idReserva, Produto produto, int quantidade, BigDecimal preco) {
        this.idPedido = idPedido;
        this.idReserva = idReserva;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public Pedido() {
        super();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    
    
}
