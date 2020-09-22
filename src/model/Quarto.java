package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Quarto {
    private int idQuarto;
    private int numeroQuarto;
    private Boolean statusQuarto;
    private CategoriaQuarto categoria = new CategoriaQuarto();
    
    public Quarto() {
        super();
    }
    
    public Quarto(int id, int numero) {
        this.idQuarto = id;
        this.numeroQuarto = numero;
    }

    public Quarto(int idQuarto, int numeroQuarto, Boolean statusQuarto, CategoriaQuarto categoria) {
        this.idQuarto = idQuarto;
        this.numeroQuarto = numeroQuarto;
        this.statusQuarto = statusQuarto;
        this.categoria = categoria;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Boolean getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(Boolean statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public CategoriaQuarto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaQuarto idCategoria) {
        this.categoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Quarto{" + "idQuarto=" + idQuarto + ", numeroQuarto=" + numeroQuarto + ", statusQuarto=" + statusQuarto + ", categoria=" + categoria + '}';
    }
    
}
