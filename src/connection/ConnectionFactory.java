package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elyneker Luciani
 */
public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pousada_freire?autoReconnect=true&useSSL=false";
    //private static final String URL = "jdbc:mysql://localhost:3306/pousada_freire?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = ""; //include

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            // throw new ClassNotFoundException("ConnectionFactory error "+e.getMessage());
            //logger.fatal("Connection getConnection: " + e);
            JOptionPane.showMessageDialog(null, "Não foi possível iniciar o sistema: " + e, "Problemas na Conexão", JOptionPane.ERROR);
            throw new RuntimeException("Erro na conexão: " + e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            //logger.fatal("Connection closeConnection: " + e);
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            //logger.fatal("Connection closeConnection: " + ex);
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            //logger.fatal("Connection closeConnection: " + ex);
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
