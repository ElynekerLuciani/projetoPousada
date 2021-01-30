package dao;

import connection.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.CaixaFinanceiro;
import model.Calcular;
import model.ContabilidadeCaixa;
import model.ReciboHospedagem;
import model.TipoMovimentacao;

/**
 *
 * @author Elyneker Luciani
 */
public class CaixaFinanceiroDAO {

    Calcular calular = new Calcular();

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
            while (rs.next()) {
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

    public ArrayList<String[]> buscarRegistrosDoCaixa() throws ClassNotFoundException, SQLException {
        ArrayList<String[]> dados = new ArrayList<>();
        String sql
                = "SELECT id_caixa, data_processamento, tipo_movimentacao.tipo_movimentacao, "
                + "descricao, id_recibo, valor "
                + "FROM caixa_financeiro "
                + "INNER JOIN tipo_movimentacao "
                + "ON tipo_movimentacao.id_tipo_movimentacao = caixa_financeiro.tipo_movimentacao;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String registros[] = new String[6];
                registros[0] = rs.getString("id_caixa");
                registros[1] = rs.getString("data_processamento");
                registros[2] = rs.getString("tipo_movimentacao");
                registros[3] = rs.getString("descricao");
                registros[4] = rs.getString("id_recibo");
                registros[5] = rs.getString("valor");
                dados.add(registros);
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiroDAO.buscarRegristrosCaixa: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return dados;
    }
    
    //Pesquisar dentro de um período de datas
     public ArrayList<String[]> buscarRegistrosPeriodoCaixa(LocalDateTime periodoInicial, LocalDateTime periodoFinal) throws ClassNotFoundException, SQLException {
        ArrayList<String[]> dados = new ArrayList<>();
        String sql
                = "SELECT id_caixa, data_processamento, tipo_movimentacao.tipo_movimentacao, "
                + "descricao, id_recibo, valor "
                + "FROM caixa_financeiro "
                + "INNER JOIN tipo_movimentacao "
                + "ON tipo_movimentacao.id_tipo_movimentacao = caixa_financeiro.tipo_movimentacao "
                + "WHERE caixa_financeiro.data_processamento BETWEEN ? and ?;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(periodoInicial));
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(periodoFinal));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String registros[] = new String[6];
                registros[0] = rs.getString("id_caixa");
                registros[1] = rs.getString("data_processamento");
                registros[2] = rs.getString("tipo_movimentacao");
                registros[3] = rs.getString("descricao");
                registros[4] = rs.getString("id_recibo");
                registros[5] = rs.getString("valor");
                dados.add(registros);
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiroDAO.buscarRegristrosCaixa: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return dados;
    }

    public ArrayList<TipoMovimentacao> listarTiposMovimentacao() throws ClassNotFoundException, SQLException {
        ArrayList<TipoMovimentacao> listaTipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_movimentacao;";
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaTipos.add(new TipoMovimentacao(
                        rs.getInt("id_tipo_movimentacao"),
                        rs.getString("tipo_movimentacao")));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiro.listarTipos: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaTipos;
    }

    public void inserirNovaMovimentacao(CaixaFinanceiro caixaFinanceiro) throws ClassNotFoundException, SQLException {
        String sql
                = "INSERT INTO caixa_financeiro(data_processamento, tipo_movimentacao, descricao, id_recibo, valor) "
                + "VALUES(?,?,?,?,?);";
        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(caixaFinanceiro.getDataProcessamento()));
            stmt.setInt(2, caixaFinanceiro.getTipoMovimentacao().getIdTipoMovimentacao());
            stmt.setString(3, caixaFinanceiro.getDescricao());
            stmt.setInt(4, caixaFinanceiro.getIdRecibo());
            stmt.setBigDecimal(5, caixaFinanceiro.getValorTotal());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiroDAO.inserirNovaMovimentacao: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
    }

    public ContabilidadeCaixa realizarSomaTotal() throws ClassNotFoundException, SQLException {
        ContabilidadeCaixa contabilidade = null;
        String sqlEntradas = "SELECT valor FROM caixa_financeiro WHERE tipo_movimentacao=1;";
        ArrayList<String[]> entradas = new ArrayList<>();
        ArrayList<String[]> saidas = new ArrayList<>();
        String sqlSaidas = "SELECT valor FROM caixa_financeiro WHERE tipo_movimentacao=2;";

        try {
            PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sqlEntradas);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String valor[] = new String[1];
                valor[0] = rs.getString("valor");
                entradas.add(valor);
            }
            stmt.close();;
            rs.close();

            BigDecimal valorEntradas = calular.somarTotal(entradas, 0);

            PreparedStatement stmt_2 = ConnectionFactory.getConnection().prepareStatement(sqlSaidas);
            ResultSet rs_2 = stmt_2.executeQuery();
            while (rs_2.next()) {
                String valor[] = new String[1];
                valor[0] = rs_2.getString("valor");
                saidas.add(valor);
            }
            stmt_2.close();;
            rs_2.close();

            BigDecimal valorSaidas = calular.somarTotal(saidas, 0);

            BigDecimal valorTotal = valorEntradas.subtract(valorSaidas);

            contabilidade = new ContabilidadeCaixa(valorEntradas, valorSaidas, valorTotal);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CaixaFinanceiroDAO.realizarSoma: " + e);
        } finally {
            ConnectionFactory.getConnection().close();
        }
        return contabilidade;
    }

}
