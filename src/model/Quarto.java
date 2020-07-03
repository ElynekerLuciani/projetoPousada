package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Quarto {
    private int idQuarto;
    private int numeroQuarto;
    private Boolean statusQuarto;
    
    public Quarto() {
        super();
    }
    
    public Quarto(int id, int numero) {
        this.idQuarto = id;
        this.numeroQuarto = numero;
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
    
}
