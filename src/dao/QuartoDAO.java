package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CategoriaQuarto;
import model.Quarto;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoDAO {

    public ArrayList<Quarto> dados = new ArrayList<>();

    public ArrayList<Quarto> quartosDisponiveis() throws SQLException, ClassNotFoundException {
        ArrayList<Quarto> quartos = new ArrayList<>();
        String sql
                = "select Quarto.numero_quarto, Quarto.id_quarto, "
                + "Quarto.id_categoria, Quarto.sujo, Reserva.status_reserva "
                + "from Quarto "
                + "left join Reserva "
                + "on Reserva.numero_quarto = Quarto.numero_quarto "
                + "AND Reserva.status_reserva <> 0 "
                + "ORDER BY Quarto.numero_quarto;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Quarto q = new Quarto();
                q.setIdQuarto(rs.getInt("id_quarto"));
                q.setNumeroQuarto(rs.getInt("numero_quarto"));
                q.getCategoria().setIdCategoriaQuarto(rs.getInt("id_categoria"));
                q.setSujo(rs.getBoolean("sujo"));
                if (rs.getInt("status_reserva") == 1) {
                    //quarto ocupado
                    q.setStatusQuarto(Boolean.TRUE);
                } else {
                    //quarto livre
                    q.setStatusQuarto(Boolean.FALSE);
                }
                quartos.add(q);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return quartos;

    }

    /**
     * Método que realiza uma busca através do id do quarto para retornar o nome
     * da categoria a que pertence o quarto.
     *
     * Passo a passo: Para acessar este método, usuário clica no QuartoComponent
     * e ao exibir a TelaHospedarCliente o contrutor da classe acessa o
     * QuartoController e no método buscarCategoriaQuarto faz o acesso a este
     * método.
     *
     * @param idCategoria
     * @return
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     *
     */
    public String buscarNomeCategoriaQuarto(int idCategoria) throws SQLException, ClassNotFoundException {
        String categoriaQuarto = null;
        String sql
                = "SELECT nome_categoria "
                + "FROM Categoria_Quarto "
                + "WHERE id_categoria_quarto = ? AND status_categoria=1;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                categoriaQuarto = rs.getString("nome_categoria");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return categoriaQuarto;
    }

    /**
     * Este método realiza uma pesquisa para buscar quantas pessoas podem ser
     * hospedadas em único quarto. Utilizando o id da categoria do quarto, o
     * método retorna uma lista com a quantidade de pessoas por quarto que será
     * exibida em um combobox na tela de reserva de quarto.
     *
     * @param idCategoria
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<CategoriaQuarto> buscarQntPessoasPorQuarto(int idCategoria) throws ClassNotFoundException, SQLException {
        ArrayList<CategoriaQuarto> qntPessoasPorQuarto = new ArrayList<>();
        try {
            String sql
                    = "SELECT id_categoria, valor_categoria, qnt_hospede "
                    + "FROM valor_categoria "
                    + "WHERE valor_categoria.id_categoria = ? "
                    + "AND valor_categoria.status_categoria = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CategoriaQuarto c = new CategoriaQuarto();
                c.setIdCategoriaQuarto(rs.getInt("id_categoria"));
                c.setPrecoCategoria(rs.getBigDecimal("valor_categoria"));
                c.setQntHospedes(rs.getInt("qnt_hospede"));
                qntPessoasPorQuarto.add(c);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();

        }
        return qntPessoasPorQuarto;
    }

    public void colocarQuartoEmManutenção(int idQuarto) {
        try {
            String sql
                    = "UPDATE quarto "
                    + "SET sujo = 1 "
                    + "WHERE id_quarto =? "
                    + "AND status_quarto = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idQuarto);
            stmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void removerManutencaoQuarto(int idQuarto) throws ClassNotFoundException, SQLException {
        try {
            String sql
                    = "UPDATE quarto "
                    + "SET sujo = 0 "
                    + "WHERE id_quarto = ? "
                    + "AND status_quarto = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idQuarto);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("dao.QuartoDAO.removerManutencaoQuarto " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
    }

    public ArrayList<String[]> buscarCategoriasQuartoCadastrado() throws ClassNotFoundException, SQLException {
        ArrayList<String[]> dados = new ArrayList<>();
        String sql
                = "SELECT id_valor_categoria, nome_categoria, qnt_hospede, valor_categoria "
                + "FROM valor_categoria "
                + "INNER JOIN categoria_quarto "
                + "ON categoria_quarto.id_categoria_quarto = valor_categoria.id_categoria;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String valores[] = new String[4];
                valores[0] = rs.getString("id_valor_categoria");
                valores[1] = rs.getString("nome_categoria");
                valores[2] = rs.getString("qnt_hospede");
                valores[3] = rs.getString("valor_categoria");
                dados.add(valores);
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

    public ArrayList<CategoriaQuarto> listarCategorias() throws ClassNotFoundException, SQLException {
        ArrayList<CategoriaQuarto> listaCategorias = new ArrayList<>();
        String sql 
                = "SELECT id_categoria_quarto, nome_categoria "
                + "FROM categoria_quarto "
                + "WHERE status_categoria = 1;"; //status 1 = ativo
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaCategorias.add(new CategoriaQuarto(
                        rs.getInt("id_categoria_quarto"), 
                        rs.getString("nome_categoria")));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("QuartoDAO.listarCategorias: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaCategorias;
    }

    public CategoriaQuarto listarCategoriasId(int idCategoria) throws ClassNotFoundException, SQLException {
         CategoriaQuarto listaCategorias = new CategoriaQuarto();
        String sql 
                = "SELECT id_categoria_quarto, nome_categoria "
                + "FROM categoria_quarto "
                + "INNER JOIN valor_categoria "
                + "ON valor_categoria.id_categoria = categoria_quarto.id_categoria_quarto "
                + "WHERE id_valor_categoria = ?;"; //status 1 = ativo
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaCategorias.setIdCategoriaQuarto( rs.getInt("id_categoria_quarto"));
                listaCategorias.setCategoriaQuarto(rs.getString("nome_categoria"));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("QuartoDAO.listarCategorias: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaCategorias;
    }

    public void editarDados(CategoriaQuarto novo) throws ClassNotFoundException, SQLException {
         try {
            String sql
                    = "UPDATE valor_categoria "
                    + "SET valor_categoria = ?, qnt_hospede = ?, id_categoria = ? "
                    + "WHERE id_valor_categoria = ?;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setBigDecimal(1, novo.getPrecoCategoria());
            stmt.setInt(2, novo.getQntHospedes());
            stmt.setInt(3, Integer.parseInt(novo.getCategoriaQuarto()));
            stmt.setInt(4, novo.getIdCategoriaQuarto());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("QuartoDAO.editarDados " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
    }

    public void cadastrarValoresCategoriaQuarto(CategoriaQuarto novo) throws ClassNotFoundException, SQLException {
         try {
            String sql
                    = "INSERT INTO valor_categoria(valor_categoria, qnt_hospede, id_categoria, status_categoria) "
                    + "VALUES(?, ?, ?, ?);";
          
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setBigDecimal(1, novo.getPrecoCategoria());
            stmt.setInt(2, novo.getQntHospedes());
            stmt.setInt(3, Integer.parseInt(novo.getCategoriaQuarto()));
            stmt.setInt(4, 1);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("QuartoDAO.cadastrarValoresCategoriaQuarto " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
    }
}
