package model;

import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elyneker Luciani
 */
public class PessoaJuridica implements ClienteStrategy {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public void cadastrarCliente(Cliente novo) throws Exception {
        try {
            clienteDAO.cadastrarClientePJ(novo);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PessoaJuridica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarCliente(Cliente cliente) {
        try {
            clienteDAO.editarDadosClientePJ(cliente);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("PessoaJuridica.editar:" + e);
        }
    }

}
