package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Endereco {
    private int idEndereco;
    private String endereco;
    private Estado estado = new Estado();
    private Cidade cidade = new Cidade();

    public Endereco() {
        super();
    }
    
    public Endereco(int idEndereco, String endereco, Estado estado, Cidade cidade) {
        this.idEndereco = idEndereco;
        this.endereco = endereco;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Endereco(String endereco, Estado estado, Cidade cidade) {
        this.endereco = endereco;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Endereco(Cidade cidade) {
        this.cidade = cidade;
    }

    public Endereco(String endereco, Cidade cidade) {
        this.endereco = endereco;
        this.cidade = cidade;
    }
    
    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
 
}
