package br.cefetmg.inf.sigap.dao;

import br.cefetmg.inf.sigap.dto.Usuario;

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

    public static int getId(long cpf, String senha){
        Connection conn = conectarDB();
        String sql = "SELECT token FROM usuario WHERE cpf = ? AND senha =?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, cpf);
            statement.setString(2, senha);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return result.getInt("id");
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
        Random rand = new Random();
        long cpf = usuario.getCpf();
        String senha = usuario.getSenha();
        String nome = usuario.getNome();
        String email = usuario.getEmail();


        String sql2 = "INSERT INTO usuario (cpf, nome, senha, email) " +
        "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql2);
            statement.setLong(1,cpf);
            statement.setString(2,nome);
            statement.setString(3,senha);
            statement.setString(4,email);
            statement.executeUpdate();
            statement.close();
            System.out.println("Usuário criado com sucesso!");
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
        String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1,cpf);
            statement.setString(2,senha);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                System.out.println("Usuário logado com sucesso!");
                System.out.println("Id: " + result.getInt("id"));
                System.out.println("---END LOGIN---");
                return true;
            }
            else {
                System.out.println("Usuário não encontrado!");
                System.out.println("---END LOGIN---");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario getUserData(int id){
        Connection conn = conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
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
