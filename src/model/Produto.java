package model;

import java.math.BigDecimal;

/**
 *
 * @author Elyneker Luciani
 */
public class Produto {
    private int idProduto;
    private String produto;
    private BigDecimal valor;
    private CategoriaProduto catProduto;

    public Produto(int idProduto, String produto, BigDecimal valor, CategoriaProduto catProduto) {
        this.idProduto = idProduto;
        this.produto = produto;
        this.valor = valor;
        this.catProduto = catProduto;
    }
    
    public Produto() {
        super();
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CategoriaProduto getCatProduto() {
        return catProduto;
    }

    public void setCatProduto(CategoriaProduto catProduto) {
        this.catProduto = catProduto;
    }

    @Override
    public String toString() {
        return getProduto();
    }
    
    
    
}
