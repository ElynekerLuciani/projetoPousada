/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elyneker Luciani
 */
public class PessoaFisica implements ClienteStrategy {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void cadastrarCliente(Cliente novo) throws Exception {
        try {
            clienteDAO.cadastrarClientePF(novo);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception(e);
            //Logger.getLogger(PessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarCliente(Cliente cliente) {
        try {
//            System.out.println("Editar cliente em pf");
//            System.out.println("##Tabela Documento##");
//            System.out.println(cliente.getDocumento().getIdDocumento());
//            System.out.println(cliente.getDocumento());
//
//            System.out.println("##Tabela Cliente##");
//            System.out.println(cliente.getIdCliente());
//            System.out.println(cliente.getNomeCliente());
//            System.out.println(cliente.getContatoCliente().getCelular());
//            System.out.println(cliente.getContatoCliente().getCelularOpcional());
//            System.out.println(cliente.getContatoCliente().getTelefone());
//
//            System.out.println("##Endereco##");
//            System.out.println(cliente.getEnderecoCliente().getIdEndereco());
//            System.out.println(cliente.getEnderecoCliente().getEndereco());
//            System.out.println(cliente.getEnderecoCliente().getCidade().getIdCidade());
//            System.out.println("Contatos:");

            clienteDAO.editarDadosClientePF(cliente);
        } catch (Exception e) {
            System.out.println("PessoaFisica.editar: " + e);
        }
    }

}
