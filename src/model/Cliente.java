package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Cliente {

    private int idCliente;
    private String nomeCliente;
    private Contato contatoCliente = new Contato();
    private Endereco enderecoCliente = new Endereco();
    private TipoCliente tipoCliente;
    private Documento documento = new Documento();

    public Cliente(int idCliente, String nomeCliente, Contato contatoCliente, Endereco enderecoCliente, TipoCliente tipoCliente, Documento documento) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.contatoCliente = contatoCliente;
        this.enderecoCliente = enderecoCliente;
        this.tipoCliente = tipoCliente;
        this.documento = documento;
    }

    public Cliente(String nomeCliente, Contato contatoCliente, Endereco enderecoCliente, TipoCliente tipoCliente, Documento documento) {
        this.nomeCliente = nomeCliente;
        this.contatoCliente = contatoCliente;
        this.enderecoCliente = enderecoCliente;
        this.tipoCliente = tipoCliente;
        this.documento = documento;
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente() {
        super();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Contato getContatoCliente() {
        return contatoCliente;
    }

    public void setContatoCliente(Contato contatoCliente) {
        this.contatoCliente = contatoCliente;
    }

    public Endereco getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(Endereco enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }

}
