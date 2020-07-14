/*
 * Esta classe contém os atributos referentes à categoria dos quartos.
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author Elyneker Luciani
 */
public class CategoriaQuarto {
    private int idCategoriaQuarto;
    private String categoriaQuarto;
    private int qntHospedes;
    private int idQuarto;
    private BigDecimal precoCategoria;

    //Construtor que não recebe o idCategoriaQuarto
    public CategoriaQuarto(String catQuarto, int qnt, int id_Quarto, BigDecimal preco) {
        this.categoriaQuarto = catQuarto;
        this.qntHospedes = qnt;
        this.idQuarto = id_Quarto;
        this.precoCategoria = preco;
    }

    //Construtor da classe com todos os atributos
    public CategoriaQuarto(int idCatQuarto, String catQuarto, int qntHospedes, int id_Quarto, BigDecimal preco) {
        this.idCategoriaQuarto = idCatQuarto;
        this.categoriaQuarto = catQuarto;
        this.qntHospedes = qntHospedes;
        this.idQuarto = id_Quarto;
        this.precoCategoria = preco;
    }
    
    //---- Getters e Setters ----//
    public int getIdCategoriaQuarto() {
        return idCategoriaQuarto;
    }

    public void setIdCategoriaQuarto(int idCategoriaQuarto) {
        this.idCategoriaQuarto = idCategoriaQuarto;
    }

    public String getCategoriaQuarto() {
        return categoriaQuarto;
    }

    public void setCategoriaQuarto(String categoriaQuarto) {
        this.categoriaQuarto = categoriaQuarto;
    }

    public int getQntHospedes() {
        return qntHospedes;
    }

    public void setQntHospedes(int qntHospedes) {
        this.qntHospedes = qntHospedes;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public BigDecimal getPrecoCategoria() {
        return precoCategoria;
    }

    public void setPrecoCategoria(BigDecimal precoCategoria) {
        this.precoCategoria = precoCategoria;
    }

}
