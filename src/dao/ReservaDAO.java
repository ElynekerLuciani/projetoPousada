/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import model.Reserva;

/**
 *
 * @author Elyneker Luciani
 */
public class ReservaDAO {
    
    public void realizarReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        String sql = 
                "INSERT INTO Reserva(id_quarto, numero_quarto, data_entrada, data_previsao_saida) " +
                "VALUES (?,?,?,?);";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, reserva.getQuarto().getIdQuarto());
            stmt.setInt(2, reserva.getQuarto().getNumeroQuarto());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(reserva.getDataEntrada()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(reserva.getPrevisaoSaida()));
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw  new SQLException("Erros em realizar reserva: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    public void encerrarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
        try {
            String sql = 
                    "UPDATE reserva " +
                    "SET reserva.data_saida = ? " +
                    "WHERE id_reserva = ? " +
                    "AND status_reserva = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(reserva.getDataSaida()));
            stmt.setInt(2, reserva.getIdReserva());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + " em dao.ReservaDAO.encerrarReserva");
        } finally {
            ConnectionFactory.getConnection().close();
        }
       
    }
    
    public void alterarStatusReserva(int numeroQuarto) throws ClassNotFoundException, SQLException {
        try {
            String sql =
                    "UPDATE reserva " +
                    "SET reserva.status_reserva = 0 " +
                    "WHERE numero_quarto = ? " +
                    "AND status_reserva = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, numeroQuarto);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("dao.ReservaDAO.alterarStatusReserva " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }
    
    public Reserva buscarDadosReserva(int idQuarto) throws ClassNotFoundException, SQLException {
        Reserva reserva = new Reserva();
        try {
            String sql = 
                    "SELECT id_reserva, id_quarto, numero_quarto, data_entrada, data_previsao_saida " +
                    "FROM reserva " +
                    "WHERE id_quarto = ? " +
                    "AND status_reserva = 1;";
//                    "SELECT reserva.id_reserva, reserva.numero_quarto, " +
//                            "reserva.data_entrada, reserva.data_previsao_saida, quarto.id_quarto " +
//                    "FROM reserva " +
//                    "LEFT JOIN quarto " +
//                    "ON reserva.numero_quarto = quarto.numero_quarto " +
//                    "WHERE reserva.numero_quarto = ? " +
//                    "AND reserva.status_reserva = 1 " +
//                    "AND quarto.status_quarto = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idQuarto);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reserva.getQuarto().setIdQuarto(rs.getInt("id_quarto"));
                reserva.getQuarto().setNumeroQuarto(rs.getInt("numero_quarto"));
                Timestamp t = rs.getTimestamp("data_entrada");
                reserva.setDataEntrada(t.toLocalDateTime());
                Timestamp p = rs.getTimestamp("data_previsao_saida");
                reserva.setPrevisaoSaida(p.toLocalDateTime());
                reserva.getQuarto().setIdQuarto(rs.getInt("id_quarto"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("dao.ReservaDAO.buscarDadosReserva: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return reserva;
    }
    
}
