package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.dao.UsuarioDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService {
    public static long extrairCpf(String cpfRawString){
        StringBuilder cpfString = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(cpfRawString);
        while (matcher.find()) {
            cpfString.append(matcher.group());
        }

        return Long.parseLong(cpfString.toString());
    }
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
    public static Usuario[] listarUsuarios(){return UsuarioDao.listarUsuarios();}
    public static boolean removerUsuario(int id, int authId){return UsuarioDao.removerUsuario(id, authId);}
    public static boolean trocarAutoridade(int id, int auth, int idAuth){return false;}
    public static boolean verificarAutoridade(int id, int auth){return false;}
    public static boolean atualizarUsuario(Usuario usuario){return UsuarioDao.atualizarUsuario(usuario);}
    public static boolean atualizarSenha(int id, String senha, String senhaOld){return UsuarioDao.atualizarSenha(id, senha, senhaOld);}
    public static boolean atualizarUsuario(int authId, Usuario usuario){return UsuarioDao.atualizarUsuario(authId, usuario );}
}
