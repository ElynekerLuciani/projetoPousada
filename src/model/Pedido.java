package model;

import java.math.BigDecimal;

/**
 *
 * @author Elyneker Luciani
 */
public class Pedido {
    private int idPedido;
    private Produto produto;
    private int quantidade;
    private BigDecimal preco;

    public Pedido(int idPedido, Produto produto, int quantidade, BigDecimal preco) {
        this.idPedido = idPedido;
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
