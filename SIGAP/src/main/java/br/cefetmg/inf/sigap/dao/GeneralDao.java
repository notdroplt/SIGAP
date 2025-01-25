package br.cefetmg.inf.sigap.dao;

import br.cefetmg.inf.sigap.backend.InitServlet;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GeneralDao {
    public static Connection conectarDB(){
        String driver = "org.postgresql.Driver";
        String protocol = "jdbc:postgresql://db:5432/sigap";
        String username = "sigap";
        String password = "sigap";

        Connection conn = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
               NoSuchMethodException e){
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(protocol, username, password);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void dropTable(String table, int id) throws Exception {
        Connection conn = conectarDB();
        try {
            String sql = "DROP TABLE " + table;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            InitServlet initServlet = new InitServlet();
            initServlet.init();
            logAction(id, "Tabela dropada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void logAction(int id, String action) {
        Connection conn = conectarDB();
        String sql = "INSERT INTO log (uid, acao, data) VALUES (?, ?, NOW())";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, action);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
