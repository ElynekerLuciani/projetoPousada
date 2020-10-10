package dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Reserva;

/**
 *
 * @author Elyneker Luciani
 */
public class ReservaDAO {
    
    /**
     * Este método recebe um objeto reserva contendo informações do cliente, do
     * quarto, horário da reserva, previsão de saída e a quantidade de pessoas
     * no quarto reservado.
     * @param reserva
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
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
    
    public void alterarStatusReserva(int idReserva) throws ClassNotFoundException, SQLException {
        try {
            String sql =
                    "UPDATE reserva " +
                    "SET reserva.status_reserva = 0 " +
                    "WHERE id_reserva = ? " +
                    "AND status_reserva = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("dao.ReservaDAO.alterarStatusReserva " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }
    
    /**
     * Neste método recebemos o id da reserva e realizamos uma busca para obter
     * todos os dados da reserva selecionada para exibir os dados na tela
     * dados reserva.
     * 
     * @param idReserva
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Reserva buscarDadosReserva(int idReserva) throws ClassNotFoundException, SQLException {
        Reserva reserva = new Reserva();
        try {
            String sql = 
                    "SELECT id_reserva, id_quarto, numero_quarto, data_entrada, data_previsao_saida " +
                    "FROM reserva " +
                    "WHERE id_reserva = ? " +
                    "AND status_reserva = 1;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idReserva);
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
    
    /**
     * Neste método buscamos encontrar quais as reservas de quartos estão
     * ativas para que possam ser exibidos nos blocos componentes na tela 
     * inicial.
     * Através da consulta sql, selecionamos os dados do quarto que está
     * ocupado e o id da reserva que será utilizada na consulta quando o
     * usuário buscar informações ao clicar no bloco componente. Os dados são
     * retornados para todas as reservas que estão com status diferente de
     * zero ( <> 0 ) pois no banco as reservas ativas possuem status default 1.
     * 
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public ArrayList<Reserva> buscarReservarAtivasParaBlocos() throws SQLException, ClassNotFoundException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try {
            String sql = 
                "select Quarto.numero_quarto, Quarto.id_quarto, " + 
                "Quarto.id_categoria, Quarto.sujo, Reserva.status_reserva, " +
                "Reserva.id_reserva " +
                "from Quarto " + 
                "left join Reserva " + 
                "on Reserva.numero_quarto = Quarto.numero_quarto " + 
                "AND Reserva.status_reserva <> 0 " + 
                "ORDER BY Quarto.numero_quarto;";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getInt("id_reserva"));
                r.getQuarto().setIdQuarto(rs.getInt("id_quarto"));
                r.getQuarto().setNumeroQuarto(rs.getInt("numero_quarto"));
                r.getQuarto().getCategoria().setIdCategoriaQuarto(rs.getInt("id_categoria"));
                r.getQuarto().setSujo(rs.getBoolean("sujo"));
                if(rs.getInt("status_reserva") == 1) {
                    //quarto ocupado, status = 1
                    r.getQuarto().setStatusQuarto(Boolean.TRUE);
                } else {
                    //quarto livre, status = 0
                    r.getQuarto().setStatusQuarto(Boolean.FALSE);
                }
                reservas.add(r);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Problema: ReservaDAO.buscarReservarAtivasParaBlocos " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return reservas;  
    }
    
    
}
