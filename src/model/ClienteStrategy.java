package model;

/**
 *
 * @author Elyneker Luciani
 */
public interface ClienteStrategy {

    public void cadastrarCliente(Cliente novo) throws Exception;

    public void editarCliente(Cliente cliente) throws Exception;

}
