package model;

import dao.ClienteDAO;
import java.sql.SQLException;

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
            clienteDAO.editarDadosClientePF(cliente);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("PessoaFisica.editar: " + e);
        }
    }

}
