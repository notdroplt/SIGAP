package br.cefetmg.inf.sigap.db;

public class UsuarioService {
    public static boolean criarUsuario(Usuario usuario){
        return UsuarioDao.adicionarUsuario(usuario);
    }
    public static boolean login(long identificador, String senha){
        return UsuarioDao.VerificarUsuario(identificador, senha);
    }
    public static int getToken(long identificador, String senha){
        return UsuarioDao.getToken(identificador, senha);
    }
    public static Usuario getUserData(int token){
        return UsuarioDao.getUserData(token);
    }
}
