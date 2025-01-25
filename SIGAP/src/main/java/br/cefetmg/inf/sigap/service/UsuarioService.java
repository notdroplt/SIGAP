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
}
