package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Documento {
    private int idDocumento;
    private Cpf cpf;
    private Cnpj cnpj;
    private String passaporte;

    public Documento() {
        super();
    }
    
    public Documento(Cpf cpf) {
        this.cpf = cpf;
    }

    public Documento(int idDocumento, Cpf cpf) {
        this.idDocumento = idDocumento;
        this.cpf = cpf;
    }

    public Documento(Cnpj cnpj) {
        this.cnpj = cnpj;
    }

    public Documento(int idDocumento, Cnpj cnpj) {
        this.idDocumento = idDocumento;
        this.cnpj = cnpj;
    }

    public Documento(String passaporte) {
        this.passaporte = passaporte;
    }

    public Documento(int idDocumento, String passaporte) {
        this.idDocumento = idDocumento;
        this.passaporte = passaporte;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Cnpj getCnpj() {
        return cnpj;
    }

    public void setCnpj(Cnpj cnpj) {
        this.cnpj = cnpj;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

//    @Override
//    public String toString() {
//        return getCpf().toString();
//    }
    
}
