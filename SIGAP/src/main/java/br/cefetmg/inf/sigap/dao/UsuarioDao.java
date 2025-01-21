package br.cefetmg.inf.sigap.dao;

import br.cefetmg.inf.sigap.dto.Usuario;

import java.sql.*;

public class UsuarioDao {

    public static int getId(String cpf, byte[] senha){
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT id FROM usuario WHERE cpf = ? AND senha =?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.setBytes(2, senha);
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
        Connection conn = GeneralDao.conectarDB();
        String cpf = usuario.getCpf();
        byte[] senha = usuario.getSenha();
        String nome = usuario.getNome();
        String email = usuario.getEmail();


        String sql2 = "INSERT INTO usuario (cpf, nome, senha, email) " +
        "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql2);
            statement.setString(1,cpf);
            statement.setString(2,nome);
            statement.setBytes(3,senha);
            statement.setString(4,email);
            statement.executeUpdate();
            statement.close();
            GeneralDao.logAction(getId(cpf, senha), "Criou o usuário " + getId(cpf, senha));
            System.out.println("Usuário criado com sucesso!");
            System.out.println("Id: " + getId(cpf, senha));
            System.out.println("Nome: " + nome);
            System.out.println("cpf: " + cpf);
            System.out.println("---END CREATE USER---");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean VerificarUsuario(String cpf, byte[] senha){
        System.out.println("---START LOGIN---");
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,cpf);
            statement.setBytes(2,senha);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                System.out.println("Usuário logado com sucesso!");
                System.out.println("Id: " + result.getInt("id"));
                System.out.println("Nome: " + result.getString("nome"));
                GeneralDao.logAction(result.getInt("id"), "usuario: " + result.getInt("id") + " Logou no sistema");
                System.out.println("---END LOGIN---");
                statement.close();
                return true;
            }
            else {
                System.out.println("Usuário não encontrado!");
                System.out.println("---END LOGIN---");
                statement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean VerificarUsuario(int id, byte[] senha){
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ? AND senha = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1,id);
            statement.setBytes(2,senha);
            ResultSet result = statement.executeQuery();
            statement.close();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario getUserData(int id){
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                System.out.println(" -- Usuário encontrado! Recuperando informação --");
                System.out.println("Id: " + result.getInt("id"));
                System.out.println("Nome: " + result.getString("nome"));
                String nome = result.getString("nome");
                String email = result.getString("email");
                byte[] senha = result.getBytes("senha");
                String cpf = result.getString("cpf");
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
        Connection conn = GeneralDao.conectarDB();
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ? WHERE id = ?";
        try {
            System.out.println("-- Atualizando usuário "+ usuario.getId()+"--");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getCpf());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
            statement.close();
            System.out.println("-- Usuário atualizado com sucesso! --");
            GeneralDao.logAction(usuario.getId(), "Atualizou os dados do usuário " + usuario.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarUsuario(int authId, Usuario usuario) {
        Connection conn = GeneralDao.conectarDB();
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ?, senha = ? WHERE id = ?";
        try {
            System.out.println("-- Atualizando usuário "+ usuario.getId()+"--");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getCpf());
            statement.setBytes(4, usuario.getSenha());
            statement.setInt(5, usuario.getId());
            statement.executeUpdate();
            statement.close();
            System.out.println("-- Usuário atualizado com sucesso! --");
            GeneralDao.logAction(authId, "Atualizou os dados do usuário " + usuario.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarSenha(int id, byte[] senha, byte[] senhaOld){
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ? AND senha = ?";
        String sql2 = "UPDATE usuario SET senha = ? WHERE id = ? AND senha = ?";
        try {
            System.out.println("-- Atualizando senha de usuário "+ id+"--");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setBytes(2,senhaOld);
            ResultSet result = statement.executeQuery();
            if (!result.next()){
                System.out.println("-- Senha antiga incorreta! --");
                return false;
            }
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setBytes(1,senha);
            statement2.setInt(2,id);
            statement2.setBytes(3,senhaOld);
            statement2.executeUpdate();
            statement2.close();
            GeneralDao.logAction(id, "Alterou a senha de usuário " + id);
            System.out.println("-- Usuário atualizado com sucesso! --");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Usuario[] listarUsuarios(){
        Connection conn = GeneralDao.conectarDB();
        String sql2 = "SELECT * FROM usuario";
        String sql = "SELECT COUNT(*) FROM usuario";
        try {
            System.out.println("-- Listando usuários --");
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
                byte[] senha = result2.getBytes("senha");
                String cpf = result2.getString("cpf");
                int id = result2.getInt("id");
                int autoridade = result2.getInt("auth");
                Usuario usuario = new Usuario(nome, email, senha, cpf, id, autoridade);
                usuarios[i] = usuario;
            }
            statement.close();
            statement2.close();
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Usuario[] listarUsuarios(String query, String category){
        Connection conn = GeneralDao.conectarDB();
        String sql2 = "SELECT * FROM usuario WHERE " + category + " LIKE ?";
        String sql = "SELECT COUNT(*) FROM usuario WHERE " + category + " LIKE ?";
        try {
            System.out.println("-- Listando usuários --");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + query + "%");
            ResultSet result = statement.executeQuery();

            int count = 0;
            if (result.next()){
                count = result.getInt(1);
            }

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, "%" + query + "%");
            ResultSet result2 = statement2.executeQuery();

            Usuario[] usuarios = new Usuario[count];
            for(int i=0; result2.next(); i++){
                String nome = result2.getString("nome");
                String email = result2.getString("email");
                byte[] senha = result2.getBytes("senha");
                String cpf = result2.getString("cpf");
                int id = result2.getInt("id");
                int autoridade = result2.getInt("auth");
                Usuario usuario = new Usuario(nome, email, senha, cpf, id, autoridade);
                usuarios[i] = usuario;
            }
            statement.close();
            statement2.close();
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean removerUsuario(int id, int authId){
        Connection conn = GeneralDao.conectarDB();
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            System.out.println("-- Removendo usuário "+ id+"--");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();
            GeneralDao.logAction(authId, "Removeu o usuário " + id);
            System.out.println("-- Usuário removido com sucesso! --");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean verificarAutoridade(int id, int auth){
        Connection conn = GeneralDao.conectarDB();
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if (!result.next())
                return false;
            return result.getInt("auth") >= auth;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean trocarAutoridade(int id, int auth, int authId){
        if(!verificarAutoridade(authId, auth))
            return false;
        Connection conn = GeneralDao.conectarDB();
        String sql = "UPDATE usuario SET auth = ? WHERE id = ?";
        try {
            System.out.println("-- Alterando autoridade do usuário "+ id+"--");
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,auth);
            statement.setInt(2,id);
            statement.executeUpdate();
            statement.close();
            GeneralDao.logAction(authId, "Alterou a autoridade do usuário " + id + " para " + auth);
            System.out.println("-- Autoridade alterada com sucesso! --");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
