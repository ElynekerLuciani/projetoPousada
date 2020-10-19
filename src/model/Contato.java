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
    private String celular;
    private String celularOpcional;
    private String telefone;

    public Contato(int idContato, String celular, String celularOpcional, String telefone) {
        this.idContato = idContato;
        this.celular = celular;
        this.celularOpcional = celularOpcional;
        this.telefone = telefone;
    }

    public Contato(String celular, String celularOpcional, String telefone) {
        this.celular = celular;
        this.celularOpcional = celularOpcional;
        this.telefone = telefone;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelularOpcional() {
        return celularOpcional;
    }

    public void setCelularOpcional(String celularOpcional) {
        this.celularOpcional = celularOpcional;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
