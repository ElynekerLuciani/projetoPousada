package dao;

import connection.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Calcular;
import model.Pedido;

/**
 *
 * @author Elyneker Luciani
 */
public class PedidoDAO {

    Calcular calc = new Calcular();

    public void adicionarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        String sql
                = "INSERT INTO pedido(id_reserva, id_produto, nome_produto, "
                + "valor_unitario, quantidade) "
                + "VALUES(?,?,?,?,?);";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, pedido.getIdReserva());
            stmt.setInt(2, pedido.getProduto().getIdProduto());
            stmt.setString(3, pedido.getProduto().getProduto());
            stmt.setBigDecimal(4, pedido.getProduto().getValor());
            stmt.setInt(5, pedido.getQuantidade());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("PedidoDAO.adicionarPedido: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }

    }

    public ArrayList<String[]> buscarProdutosConsumidos(int idReservaQuarto) throws ClassNotFoundException, SQLException {
        ArrayList<String[]> consumidos = new ArrayList<>();
        BigDecimal valorTotalConsumido = new BigDecimal("0.00");
        String sql
                = "SELECT id_pedido, nome_produto, valor_unitario, quantidade "
                + "FROM pedido "
                + "WHERE id_reserva = ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idReservaQuarto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String pedido[] = new String[5];
                pedido[0] = rs.getString("id_pedido");
                pedido[1] = rs.getString("nome_produto");
                pedido[2] = rs.getString("valor_unitario");
                pedido[3] = rs.getString("quantidade");

                String valorTotal = String.valueOf(
                        calc.calcularPrecoProdutoQuantidade(
                                rs.getString("valor_unitario"),
                                Integer.valueOf(rs.getString("quantidade"))));

                pedido[4] = valorTotal;

                valorTotalConsumido = valorTotalConsumido.add(new BigDecimal(valorTotal));

                consumidos.add(pedido);
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("PedidoDAO.buscarProdutosConsumidos: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return consumidos;
    }

}
