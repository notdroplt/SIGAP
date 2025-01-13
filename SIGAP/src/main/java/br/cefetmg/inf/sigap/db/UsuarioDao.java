package br.cefetmg.inf.sigap.db;

import java.sql.*;
import java.util.Random;

public class UsuarioDao {
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

    public static void createTable(){
        System.out.println("---START CREATE TABLE---");
        Connection conn = conectarDB();
        String sql = "DROP TABLE IF EXISTS usuario";
        String sql2 = "CREATE TABLE IF NOT EXISTS usuario (" +
                "    token INT PRIMARY KEY," +
                "    cpf BIGINT NOT NULL," +
                "    nome VARCHAR(255) NOT NULL," +
                "    senha VARCHAR(255) NOT NULL," +
                "    email VARCHAR(255) NOT NULL" +
                ");";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "SELECT column_name " +
                "FROM information_schema.columns " +
                "WHERE table_name = 'usuario';";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String columnName = result.getString("column_name");
                System.out.println(columnName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("---END CREATE TABLE---");
    }

    public static int getToken(long cpf, String senha){
        Connection conn = conectarDB();
        String sql = "SELECT token FROM usuario WHERE cpf = ? AND senha =?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cpf);
            statement.setString(2, senha);
            ResultSet result = statement.executeQuery();
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
        System.out.println("---START CREATE USER---");
        Connection conn = conectarDB();
        createTable();
        Random rand = new Random();
        long cpf = usuario.getCpf();
        String senha = usuario.getSenha();
        String nome = usuario.getNome();
        String email = usuario.getEmail();
        int token = rand.nextInt();

        String sql = "SELECT 1 FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,token);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                token = rand.nextInt();
                result = statement.executeQuery();
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        sql = "INSERT INTO usuario (id, cpf, nome, senha, email) " +
        "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,token);
            statement.setLong(2,cpf);
            statement.setString(3,nome);
            statement.setString(4,senha);
            statement.setString(5,email);
            statement.executeUpdate();
            statement.close();
            System.out.println("---END CREATE USER---");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean VerificarUsuario(long cpf, String senha){
        System.out.println("---START LOGIN---");
        Connection conn = conectarDB();
        createTable();
        String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1,cpf);
            statement.setString(2,senha);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                System.out.println("---END LOGIN---");
                return true;
            }
            else {
                System.out.println("---END LOGIN---");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario getUserData(int token){
        Connection conn = conectarDB();
        String sql = "SELECT * FROM usuario WHERE token = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,token);
            ResultSet result = statement.executeQuery();
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
