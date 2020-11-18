package model;

/**
 *
 * @author Elyneker
 */
public class Estado {

    private int idEstado = 0;
    private String nomeEstado;

    public Estado() {
        super();
    }

    public Estado(int id, String nome) {
        this.idEstado = id;
        this.nomeEstado = nome;
    }

    public Estado(String nome) {
        this.nomeEstado = nome;
    }

    public Estado(int id) {
        this.idEstado = id;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    @Override
    public String toString() {
        return getNomeEstado();
    }

}
