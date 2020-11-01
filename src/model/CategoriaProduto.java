package model;

/**
 *
 * @author Elyneker Luciani
 */
public class CategoriaProduto {
    private int idCatProduto;
    private String nomeCategoria;

    public CategoriaProduto(int idCatProduto, String nomeCategoria) {
        this.idCatProduto = idCatProduto;
        this.nomeCategoria = nomeCategoria;
    }
    
    public CategoriaProduto() {
        super();
    }

    public int getIdCatProduto() {
        return idCatProduto;
    }

    public void setIdCatProduto(int idCatProduto) {
        this.idCatProduto = idCatProduto;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return getNomeCategoria();
    }
    
    
}
