/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ClienteDAO;

/**
 *
 * @author Elyneker Luciani
 */
public class Estrangeiro implements ClienteStrategy {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void cadastrarCliente(Cliente novo) {
        try {
            clienteDAO.cadastrarClienteEstrangeiro(novo);
        } catch (Exception e) {
            System.out.println("Estrangeiro.cadastrar: " + e);
        }
    }

    @Override
    public void editarCliente(Cliente cliente) {
        try {
            clienteDAO.editarDadosClienteES(cliente);
        } catch (Exception e) {
            System.out.println("Estrangeiro.editar: " + e);
        }
    }
    
}
