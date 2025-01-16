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
    public static int getId(long cpf, String senha){
        return UsuarioDao.getId(cpf, senha);
    }
    public static Usuario getUserData(int id){
        return UsuarioDao.getUserData(id);
    }
    public static boolean trocarSenha(int id, String senhaVelha, String senhaNova){return false;}
    public static boolean removerUsuario(int id, int idAuth){return false;}
    public static boolean trocarAutoridade(int id, int auth, int idAuth){return false;}
    public static boolean verificarAutoridade(int id, int auth){return false;}
    public static boolean atualizarUsuario(int id, Usuario usuario){return UsuarioDao.atualizarUsuario(id, usuario);}
    public static boolean atualizarSenha(int id, String senha, String senhaOld){return UsuarioDao.atualizarSenha(id, senha, senhaOld);}
}
