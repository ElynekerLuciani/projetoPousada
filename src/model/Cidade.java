package model;

/**
 *
 * @author Elyneker
 */
public class Cidade {
    private int idCidade;
    private String nomeCidade;
    private int idEstado;
    
    public Cidade(int id, String nome, int estado) {
        this.idCidade = id;
        this.nomeCidade = nome;
        this.idEstado = estado;
    }
    
    public Cidade (String nome, int estado) {
        this.nomeCidade = nome;
        this.idEstado = estado;
    }
    
    public Cidade (int cidade) {
        this.idCidade = cidade;
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
