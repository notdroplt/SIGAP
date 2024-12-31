package br.cefetmg.inf.sigap.db;

import br.cefetmg.inf.sigap.db.Usuario;
import java.sql.*;

public class UsuarioDao {
    private static Connection conectarDB(){
        String driver = "";
        String protocol = "";
        String database = "database";
        String username = "admin";
        String password = "admin";

        Connection conn = null;
        Statement s;
        ResultSet rs;

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
            conn = DriverManager.getConnection(protocol + database, username, password);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static boolean adicionarUsuario(Usuario usuario){
        Connection conn = conectarDB();
    }
    public static boolean VerificarUsuario(long identificador, String senha){
        Connection conn = conectarDB();
    }
}
