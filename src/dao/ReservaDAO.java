/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Reserva;

/**
 *
 * @author Elyneker Luciani
 */
public class ReservaDAO {
    
    public void realizarReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        String sql = 
                "INSERT INTO Reserva(numero_quarto, data_entrada, data_previsao_saida) " +
                "VALUES (?,?,?);";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, reserva.getNumeroQuarto().getNumeroQuarto());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(reserva.getDataEntrada()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(reserva.getPrevisaoSaida()));
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw  new SQLException("Erros em realizar reserva: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    public void encerrarReserva(int numeroQuarto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
