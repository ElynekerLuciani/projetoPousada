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
public class PessoaFisica implements ClienteStrategy{
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void cadastrarCliente(Cliente novo) throws Exception {
//        System.out.println("Cadastrar cliente em pf");
//        System.out.println(novo.getTipoCliente());
//        System.out.println(novo.getNomeCliente());
//        System.out.println(novo.getDocumento());
//         System.out.println("Endereco:");
//         System.out.println(novo.getEnderecoCliente().getEndereco());
//         System.out.println(novo.getEnderecoCliente().getCidade().getIdCidade());
//         System.out.println("Contatos:");
//         System.out.println(novo.getContatoCliente().getCelular());
//         System.out.println(novo.getContatoCliente().getCelularOpcional());
//         System.out.println(novo.getContatoCliente().getTelefone());
         System.out.println("###############################");
        
        try {
            clienteDAO.cadastrarClientePF(novo);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception(e);
            //Logger.getLogger(PessoaFisica.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }

    @Override
    public void editarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
