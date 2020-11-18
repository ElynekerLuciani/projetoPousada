package model;

import dao.ClienteDAO;
import java.sql.SQLException;

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
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Estrangeiro.cadastrar: " + e);
        }
    }

    @Override
    public void editarCliente(Cliente cliente) {
        try {
            clienteDAO.editarDadosClienteES(cliente);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Estrangeiro.editar: " + e);
        }
    }

}
