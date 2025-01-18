package br.cefetmg.inf.sigap.dao;

import br.cefetmg.inf.sigap.backend.InitServlet;
import br.cefetmg.inf.sigap.dto.Usuario;

import java.sql.*;
import java.util.Random;;

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
        String sql = "SELECT id FROM usuario WHERE cpf = ? AND senha =?";
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
                int autoridade = result.getInt("auth");
                return new Usuario(nome, email, senha, cpf, id, autoridade);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean atualizarUsuario(Usuario usuario) {
        Connection conn = conectarDB();
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ? WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setLong(3, usuario.getCpf());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarUsuario(int authId, Usuario usuario) {
        Connection conn = conectarDB();
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ? WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setLong(3, usuario.getCpf());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarSenha(int id, String senha, String senhaOld){
        Connection conn = conectarDB();
        String sql = "UPDATE usuario SET senha = ? WHERE id = ? AND senha = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,senha);
            statement.setInt(2,id);
            statement.setString(3,senhaOld);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario[] listarUsuarios(){
        Connection conn = conectarDB();
        String sql2 = "SELECT * FROM usuario";
        String sql = "SELECT COUNT(*) FROM usuario";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int count = 0;
            if (result.next()){
                count = result.getInt(1);
            }

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            ResultSet result2 = statement2.executeQuery();

            Usuario[] usuarios = new Usuario[count];
            for(int i=0; result2.next(); i++){
                String nome = result2.getString("nome");
                String email = result2.getString("email");
                String senha = result2.getString("senha");
                long cpf = result2.getLong("cpf");
                int id = result2.getInt("id");
                int autoridade = result2.getInt("auth");
                Usuario usuario = new Usuario(nome, email, senha, cpf, id, autoridade);
                usuarios[i] = usuario;
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean removerUsuario(int id, int authId){
        Connection conn = conectarDB();
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean verificarAutoridade(int id, int auth){
        Connection conn = conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if (!result.next())
                return false;
            if (result.getInt("auth") >= auth)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean trocarAutoridade(int id, int auth, int authId){
        if(!verificarAutoridade(authId, auth))
            return false;
        Connection conn = conectarDB();
        String sql = "UPDATE usuario SET auth = ? WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,auth);
            statement.setInt(2,id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
