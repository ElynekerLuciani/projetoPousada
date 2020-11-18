package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cidade;
import model.Estado;

/**
 *
 * @author Elyneker Luciani
 */
public class EstadoCidadeDAO {

    public ArrayList<Estado> listarEstados() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Estado ORDER BY id";
        ArrayList<Estado> listaDeEstados = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaDeEstados.add(new Estado(rs.getInt("id"), rs.getString("nome")));
            }
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }
        return listaDeEstados;
    }

    public ArrayList<Cidade> listarCidades(int idEstado) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM cidade WHERE estado = ? ORDER BY id";
        ArrayList<Cidade> listaDeCidades = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            stmt.setInt(1, idEstado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaDeCidades.add(new Cidade(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("estado")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("EstadoCidadeDAO.listarCidades: " + e);
        } finally {
            connection.ConnectionFactory.getConnection().close();
        }

        return listaDeCidades;
    }

}
