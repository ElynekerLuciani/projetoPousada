package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CategoriaQuarto;

/**
 *
 * @author Elyneker Luciani
 */
public class CategoriaQuartoDAO {

    public CategoriaQuarto buscarDadosCategoria(int qnthospede) {
        CategoriaQuarto categoria = new CategoriaQuarto();
        try {
            String sql = "";
            PreparedStatement stmt = connection.ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("CategoriaQuartoDAO.buscarDadosCategoria: " + e);
        } finally {

        }
        return categoria;
    }

}
