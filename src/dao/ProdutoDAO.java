package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CategoriaProduto;
import model.Produto;

/**
 *
 * @author Elyneker Luciani
 */
public class ProdutoDAO {

    public ArrayList<CategoriaProduto> listarCategorias() throws ClassNotFoundException, SQLException {
        ArrayList<CategoriaProduto> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_produto ORDER BY id_cat_produto;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaCategorias.add(new CategoriaProduto(
                        rs.getInt("id_cat_produto"),
                        rs.getString("nome_categoria")));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.listarCategorias: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaCategorias;
    }

    public void cadastrarProduto(Produto produto) throws ClassNotFoundException, SQLException {
        try {
            String sql
                    = "INSERT INTO produtos(nome_produto, id_cat_produto, valor_produto) "
                    + "VALUES(?,?,?);";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, produto.getProduto());
            stmt.setInt(2, produto.getCatProduto().getIdCatProduto());
            stmt.setBigDecimal(3, produto.getValor());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.cadastrarProduto " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
    }

    public void editarProduto(Produto produto) throws ClassNotFoundException, SQLException {
        try {
            String sql
                    = "UPDATE produtos "
                    + "SET nome_produto = ?, valor_produto = ? "
                    + "WHERE id_produto = ?;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setString(1, produto.getProduto());
            stmt.setBigDecimal(2, produto.getValor());
            stmt.setInt(3, produto.getIdProduto());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.editarProduto " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
    }

    public ArrayList<Produto> listarProdutos(int idCategoria) throws ClassNotFoundException, SQLException {
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE id_cat_produto = ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaProduto.add(new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nome_produto"),
                        rs.getBigDecimal("valor_produto"),
                        new CategoriaProduto(rs.getInt("id_cat_produto"), "")));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.listarProdutos: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaProduto;
    }

    public ArrayList<String[]> buscarProdutosCadastrados() throws ClassNotFoundException, SQLException {
        ArrayList<String[]> dados = new ArrayList<>();
        String sql
                = "SELECT id_produto, nome_produto, nome_categoria, valor_produto "
                + "FROM produtos "
                + "INNER JOIN categoria_produto "
                + "ON categoria_produto.id_cat_produto = produtos.id_cat_produto";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String produtos[] = new String[4];
                produtos[0] = rs.getString("id_produto");
                produtos[1] = rs.getString("nome_produto").toUpperCase();
                produtos[2] = rs.getString("nome_categoria");
                produtos[3] = rs.getString("valor_produto");
                dados.add(produtos);
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.buscarProdutos: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return dados;
    }

    public void removerProdutoConsumido(int idProduto) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM pedido WHERE id_pedido = ? ;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.removerProdutoConsumido: " + 2);
        } finally {
            ConnectionFactory.getConnection().close();
        }

    }

    public void excluirProduto(int idProduto) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM produtos WHERE id_produto = ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ProdutoDAO.removerProdutoConsumido: " + 2);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    public CategoriaProduto listarCategoriasPorId(int idProduto) throws ClassNotFoundException, SQLException {
        CategoriaProduto categoria = new CategoriaProduto();
        String sql 
                = "SELECT categoria_produto.id_cat_produto , categoria_produto.nome_categoria " 
                + "FROM categoria_produto " 
                + "INNER JOIN produtos " 
                + "ON produtos.id_cat_produto = categoria_produto.id_cat_produto " 
                + "WHERE produtos.id_produto = ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categoria.setIdCatProduto(rs.getInt("id_cat_produto"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
           System.out.println("QuartoDAO.listarCategorias: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return categoria;
    }
}
