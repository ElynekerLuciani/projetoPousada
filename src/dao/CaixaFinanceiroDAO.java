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
import model.ReciboHospedagem;

/**
 *
 * @author Elyneker Luciani
 */
public class CaixaFinanceiroDAO {

    public void registrarCaixa(ReciboHospedagem recibo) throws ClassNotFoundException, SQLException {
        String sqlRecibo
                = "INSERT INTO recibo(id_reserva, valor_diarias, valor_consumo, valor_desconto) " 
                + "VALUES(?,?,?,?);";
        
        String sqlCaixaFinanceiro 
                = "INSERT INTO caixa_financeiro(data_processamento, tipo_movimentacao, " 
                + "descricao, id_recibo, valor) " 
                + "VALUES(?, ?, ?, ?, ?);";
        try {
            //Inserindo dados na tabela recibo
            PreparedStatement stmt_1 = connection.ConnectionFactory.getConnection().prepareStatement(sqlRecibo);
            stmt_1.setInt(1, recibo.getIdReserva());
            stmt_1.setBigDecimal(2, recibo.getValorDiaria());
            stmt_1.setBigDecimal(3, recibo.getValorConsumido());
            stmt_1.setBigDecimal(4, recibo.getDesconto());
            stmt_1.executeUpdate();
            stmt_1.close();
            
            String idReserva = buscarIdRecibo(recibo.getIdReserva());
            
            PreparedStatement stmt_2 = connection.ConnectionFactory.getConnection().prepareStatement(sqlCaixaFinanceiro);
            stmt_2.setTimestamp(1, java.sql.Timestamp.valueOf(recibo.getDataProcessamento()));
            stmt_2.setInt(2, 1); //valor 1 referente a entrada
            stmt_2.setString(3, "Valor de Hospedagem");
            stmt_2.setString(4, String.valueOf(idReserva));
            stmt_2.setBigDecimal(5, recibo.getValorTotalPagar());
            stmt_2.executeUpdate();
            stmt_2.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiro.regristrarCaixa: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    private String buscarIdRecibo(int idReserva) throws ClassNotFoundException, SQLException {
        String idRecibo = null;
        String sql = "SELECT id_recibo FROM recibo WHERE id_reserva = ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                idRecibo = rs.getString("id_recibo");
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiroDAO.buscarIdRecibo: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return idRecibo;
    }
    
}
