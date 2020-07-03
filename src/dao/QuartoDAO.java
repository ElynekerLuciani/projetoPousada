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
                "select Quarto.numero_quarto, Reserva.status_reserva " +
                "from Quarto " + 
                "left join Reserva " + 
                "on Reserva.numero_quarto = Quarto.numero_quarto " + 
                "AND Reserva.status_reserva <> 0;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Quarto q = new Quarto();
                q.setNumeroQuarto(rs.getInt("numero_quarto"));
                if(rs.getInt("status_reserva") != 1) {
                    q.setStatusQuarto(Boolean.FALSE);
                } else {
                    q.setStatusQuarto(Boolean.TRUE);
                }
                quartos.add(q);
            }
        } catch (Exception e) {
            throw new SQLException("Problema: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return quartos;
        
    }
}
