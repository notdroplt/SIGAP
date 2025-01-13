package br.cefetmg.inf.sigap.db;

public class UsuarioService {
    public static boolean criarUsuario(Usuario usuario){
        return UsuarioDao.adicionarUsuario(usuario);
    }
    public static boolean login(long cpf, String senha){
        return UsuarioDao.VerificarUsuario(cpf, senha);
    }
    public static int getToken(long cpf, String senha){
        return UsuarioDao.getToken(cpf, senha);
    }
    public static Usuario getUserData(int token){
        return UsuarioDao.getUserData(token);
    }
}
