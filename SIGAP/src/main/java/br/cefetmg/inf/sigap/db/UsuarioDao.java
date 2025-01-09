package br.cefetmg.inf.sigap.db;

import java.sql.*;
import java.util.Random;

public class UsuarioDao {
    private static Connection conectarDB(){
        String driver = "org.postgresql.Driver";
        String protocol = "jdbc:postgresql://localhost:5432/postgres?";
        String database = "sigap_bd";
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
            conn = DriverManager.getConnection(protocol + database, username, password);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    static Connection conn = conectarDB();

    public static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                "token INT PRIMARY KEY" +
                "cpf BIGINT" +
                "nome VARCHAR(255)" +
                "senha VARCHAR(255)" +
                "email VARCHAR(255)" +
                ")";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getToken(long cpf, String senha){
        String sql = "SELECT token FROM usuario WHERE cpf = " + cpf + " AND senha = " + senha;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return result.getInt("token");
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static boolean adicionarUsuario(Usuario usuario){
        createTable();
        Random rand = new Random();
        long cpf = usuario.getCpf();
        String senha = usuario.getSenha();
        String nome = usuario.getNome();
        String email = usuario.getEmail();
        int token = rand.nextInt();

        String sql = "INSERT INTO usuario (token, cpf, nome, senha, email) VALUES ("+ token + ", " + cpf + ", " + nome + ", " + senha + ", " + email + ")";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean VerificarUsuario(long identificador, String senha){
        createTable();
        String sql = "SELECT * FROM usuario WHERE cpf = " + identificador + " AND senha = " + senha;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario getUserData(int token){
        String sql = "SELECT * FROM usuario WHERE token = " + token;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                String nome = result.getString("nome");
                String email = result.getString("email");
                String senha = result.getString("senha");
                long cpf = result.getLong("cpf");
                Usuario usuario = new Usuario(nome, email, senha, cpf);
                return usuario;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
