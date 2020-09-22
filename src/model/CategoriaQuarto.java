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
    private BigDecimal precoCategoria;

    public CategoriaQuarto() {
        super ();
    }
    
    //Construtor que não recebe o idCategoriaQuarto
    public CategoriaQuarto(String catQuarto, int qnt, BigDecimal preco) {
        this.categoriaQuarto = catQuarto;
        this.qntHospedes = qnt;
        this.precoCategoria = preco;
    }

    //Construtor da classe com todos os atributos
    public CategoriaQuarto(int idCatQuarto, String catQuarto, int qntHospedes, BigDecimal preco) {
        this.idCategoriaQuarto = idCatQuarto;
        this.categoriaQuarto = catQuarto;
        this.qntHospedes = qntHospedes;
        this.precoCategoria = preco;
    }
    
     //Construtor da classe usado para pegar a qnt de pessoas por quarto
    public CategoriaQuarto(int idCatQuarto, int qntHospedes, BigDecimal preco) {
        this.idCategoriaQuarto = idCatQuarto;
        this.precoCategoria = preco;
        this.qntHospedes = qntHospedes;  
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

    public BigDecimal getPrecoCategoria() {
        return precoCategoria;
    }

    public void setPrecoCategoria(BigDecimal precoCategoria) {
        this.precoCategoria = precoCategoria;
    }

   
    
   
    
}
