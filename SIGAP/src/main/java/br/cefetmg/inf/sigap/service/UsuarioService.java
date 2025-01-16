package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.dao.UsuarioDao;

public class UsuarioService {
    public static boolean criarUsuario(Usuario usuario){
        return UsuarioDao.adicionarUsuario(usuario);
    }
    public static boolean login(long cpf, String senha){
        return UsuarioDao.VerificarUsuario(cpf, senha);
    }
    public static int getToken(long cpf, String senha){
        return UsuarioDao.getId(cpf, senha);
    }
    public static Usuario getUserData(int token){
        return UsuarioDao.getUserData(token);
    }
    public static boolean trocarSenha(int token, String senhaVelha, String senhaNova){return false;}
    public static boolean removerUsuario(int token, int tokenAuth){return false;}
    public static boolean trocarAutoridade(int token, int auth, int tokenAuth){return false;}
    public static boolean verificarAutoridade(int token, int auth){return false;}

}
