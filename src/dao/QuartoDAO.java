package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                "select Quarto.numero_quarto, Quarto.id_quarto, Reserva.status_reserva " +
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
     */
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
    }
}
