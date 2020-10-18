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
public interface ClienteStrategy {
    
    public void cadastrarCliente(Cliente novo);
    
    public void editarCliente(Cliente cliente);
    
}
