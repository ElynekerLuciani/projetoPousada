package model;

/**
 *
 * @author Elyneker
 */
public class Cidade {

    private int idCidade = 0;
    private String nomeCidade;
    private int idEstado = 0;

    public Cidade(int id, String nome, int estado) {
        this.idCidade = id;
        this.nomeCidade = nome;
        this.idEstado = estado;
    }

    public Cidade(String nome, int estado) {
        this.nomeCidade = nome;
        this.idEstado = estado;
    }

    public Cidade(int cidade, int estado) {
        this.idCidade = cidade;
        this.idEstado = estado;
    }

    public Cidade() {
        super();
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return getNomeCidade();
    }

}
