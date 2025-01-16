package br.cefetmg.inf.sigap.dao;

import br.cefetmg.inf.sigap.backend.InitServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GeneralDao {
    private static Connection conectarDB(){
        String driver = "org.postgresql.Driver";
        String protocol = "jdbc:postgresql://db:5432/sigap";
        String username = "sigap";
        String password = "sigap";

        Connection conn = null;

        try {
            Class.forName(driver).newInstance();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
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
    public static void dropTable(String sql) throws Exception {
        Connection conn = conectarDB();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            InitServlet initServlet = new InitServlet();
            initServlet.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
