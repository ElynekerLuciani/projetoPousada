package dao;

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
        String sql = 
                "select Quarto.numero_quarto, Quarto.id_quarto, " + 
                "Quarto.id_categoria, Quarto.sujo, Reserva.status_reserva " +
                "from Quarto " + 
                "left join Reserva " + 
                "on Reserva.numero_quarto = Quarto.numero_quarto " + 
                "AND Reserva.status_reserva <> 0 " + 
                "ORDER BY Quarto.numero_quarto;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Quarto q = new Quarto();
                q.setIdQuarto(rs.getInt("id_quarto"));
                q.setNumeroQuarto(rs.getInt("numero_quarto"));
                q.getCategoria().setIdCategoriaQuarto(rs.getInt("id_categoria"));
                q.setSujo(rs.getBoolean("sujo"));
                if(rs.getInt("status_reserva") == 1) {
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
     * Método que realiza uma busca através do id do quarto para retornar o 
     * nome da categoria a que pertence o quarto. 
     * 
     * Passo a passo:
     * Para acessar este método, usuário clica no QuartoComponent e ao exibir a 
     * TelaHospedarCliente o contrutor da classe acessa o QuartoController e no 
     * método buscarCategoriaQuarto faz o acesso a este método.
     * @param idQuarto
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
    
    public String burcarCategoriaQuarto(int idQuarto) throws SQLException, ClassNotFoundException {
        String categoriaQuarto = null;
        String sql = 
                "SELECT nome_categoria " + 
                "FROM Quarto join Categoria_Quarto " + 
                "ON id_categoria_quarto = id_categoria " +
                "WHERE id_quarto = ? AND status_categoria=1;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idQuarto);
            ResultSet rs = stmt.executeQuery();
            if(rs != null && rs.next()) {
                categoriaQuarto = rs.getString("nome_categoria");
            }      
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return categoriaQuarto;
    } */
    
     /**
     * Método que realiza uma busca através do id do quarto para retornar o 
     * nome da categoria a que pertence o quarto. 
     * 
     * Passo a passo:
     * Para acessar este método, usuário clica no QuartoComponent e ao exibir a 
     * TelaHospedarCliente o contrutor da classe acessa o QuartoController e no 
     * método buscarCategoriaQuarto faz o acesso a este método.
     * @param idCategoria
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     * */
    
    public String buscarNomeCategoriaQuarto(int idCategoria) throws SQLException, ClassNotFoundException {
        String categoriaQuarto = null;
        String sql = 
                "SELECT nome_categoria " +
                "FROM Categoria_Quarto " +
                "WHERE id_categoria_quarto = ? AND status_categoria=1;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if(rs != null && rs.next()) {
                categoriaQuarto = rs.getString("nome_categoria");
            }      
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return categoriaQuarto;
    }
    
     public ArrayList<CategoriaQuarto> buscarQntPessoasPorQuarto(int idCategoria) throws ClassNotFoundException, SQLException {
         ArrayList<CategoriaQuarto> qntPessoasPorQuarto = new ArrayList<>();
         try {
             String sql =
                     "SELECT id_categoria, valor_categoria, qnt_hospede " +
                     "FROM valor_categoria " +
                     "WHERE valor_categoria.id_categoria = ? " +
                     "AND valor_categoria.status_categoria = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CategoriaQuarto c = new CategoriaQuarto();
                c.setIdCategoriaQuarto(rs.getInt("id_categoria"));
                c.setPrecoCategoria(rs.getBigDecimal("valor_categoria"));
                c.setQntHospedes(rs.getInt("qnt_hospede"));
                qntPessoasPorQuarto.add(c);
            }
         } catch (Exception e) {
             throw new SQLException("Problema: " + e);
         } finally {
             connection.ConnectionFactory.getConnection().close();
         
         }
        return qntPessoasPorQuarto;
    }
     
     
     public void colocarQuartoEmManutenção(int idQuarto) {
         try {
             String sql = 
                     "UPDATE quarto " +
                     "SET sujo = 1 " +
                     "WHERE id_quarto =? " +
                     "AND status_quarto = 1;";    
             PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
             stmt.setInt(1, idQuarto);
           stmt.executeUpdate();
         } catch (Exception e) {
         }
     }
     
     public void removerManutencaoQuarto(int idQuarto) throws ClassNotFoundException, SQLException {
         try {
             String sql = 
                     "UPDATE quarto " +
                     "SET sujo = 0 " +
                     "WHERE id_quarto = ? " +
                     "AND status_quarto = 1;";
             PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
             stmt.setInt(1, idQuarto);
             stmt.executeUpdate();
         } catch (Exception e) {
             System.out.println("dao.QuartoDAO.removerManutencaoQuarto " + e);
         } finally {
             connection.ConnectionFactory.getConnection().close();
         }
     }
}
