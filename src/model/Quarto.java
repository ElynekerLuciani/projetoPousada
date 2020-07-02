package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Quarto {
    private int idQuarto;
    private int numeroQuarto;
    
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
    
    
    
}
