package controller;

import dao.ClienteDAO;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import model.TableModelPesquisaCliente;
import view.TelaPesquisaCliente;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaPesquisaClienteController {

    private TelaPesquisaCliente pesquisaCliente;
    private Cliente cliente;
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void setTelaPesquisaCliente(TelaPesquisaCliente t) {
        this.pesquisaCliente = t;
    }

    public void executarKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10:
                pesquisar();
                break;
        }
    }

    public void carregarTabelaClientes() {
        try {
            ArrayList<String[]> resultadoPesquisa = clienteDAO.listarTodosClientes();
            if (!resultadoPesquisa.isEmpty()) {
                pesquisaCliente.getjTableDadosClientes().setModel(new TableModelPesquisaCliente(resultadoPesquisa));
            } else {
                JOptionPane.showMessageDialog(null, "Não existe clientes cadastrados.", "Pesquisa não realizada!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("TelaPesquisaClienteController.carregarTabela: " + e);
        }
    }

    private void pesquisar() {
        String pesquisar = pesquisaCliente.getjTextFieldPesquisarCliente().getText()
                .trim().replace(".", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "")
                .replace(".", "");
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            if (!pesquisar.isEmpty()) {
                resultado = clienteDAO.buscarNomeOuDocumentoCliente(pesquisar);
                if (!resultado.isEmpty()) {
                    pesquisaCliente.getjTableDadosClientes().setModel(new TableModelPesquisaCliente(resultado));
                }
            } else {
                carregarTabelaClientes();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("ReservaController.pesquisar: " + e);
        }
    }

}
