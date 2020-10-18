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
public class PessoaFisica implements ClienteStrategy{

    @Override
    public void cadastrarCliente(Cliente novo) {
//        Cliente novo1 = new Cliente(novo);
        System.out.println("Cadastrar cliente em pf");
        System.out.println(novo.getNomeCliente());
        System.out.println(novo.getTipoCliente());
        
    }

    @Override
    public void editarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
