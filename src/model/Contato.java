/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elyneker Luciani
 */
public class Contato {
    private int idContato;
    private String telefone;
    private String telefoneEmergencia;

    public Contato(int idContato, String telefone, String telefoneEmergencia) {
        this.idContato = idContato;
        this.telefone = telefone;
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public Contato(String telefone, String telefoneEmergencia) {
        this.telefone = telefone;
        this.telefoneEmergencia = telefoneEmergencia;
    }
    
    public Contato() {
        super();
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }
    
    
}
