package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.dao.UsuarioDao;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public static boolean login(long cpf, byte[] senha){
        return UsuarioDao.VerificarUsuario(cpf, senha);
    }
    public static boolean verificarUsuario(int id, byte[] senha){
        return UsuarioDao.VerificarUsuario(id, senha);
    }
    public static int getId(long cpf, byte[] senha){
        return UsuarioDao.getId(cpf, senha);
    }
    public static Usuario getUserData(int id){
        return UsuarioDao.getUserData(id);
    }
    public static Usuario[] listarUsuarios(){return UsuarioDao.listarUsuarios();}
    public static boolean removerUsuario(int id, int authId){return UsuarioDao.removerUsuario(id, authId);}
    public static boolean trocarAutoridade(int id, int auth, int authId){return UsuarioDao.trocarAutoridade(id, auth, authId);}
    public static boolean verificarAutoridade(int id, int auth){return UsuarioDao.verificarAutoridade(id, auth);}
    public static boolean atualizarUsuario(Usuario usuario){return UsuarioDao.atualizarUsuario(usuario);}
    public static boolean atualizarSenha(int id, byte[] senha, byte[] senhaOld){return UsuarioDao.atualizarSenha(id, senha, senhaOld);}
    public static boolean atualizarUsuario(int authId, Usuario usuario){return UsuarioDao.atualizarUsuario(authId, usuario );}
    public static byte[] hashSenha(String senha){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert digest != null;
        return digest.digest(senha.getBytes(StandardCharsets.UTF_8));
    }
    public static void printPage(PrintWriter out, String content){
        String header = "<html lang=\"pt-br\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>CEFET-MG - SIGAP</title>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <header>\n" +
                "        <div class=\"title\">\n" +
                "            <h1>CEFET-MG - SIGAP -</h1>\n" +
                "            <span>Sistema Integrado de Gestão de Achados e Perdidos</span>\n" +
                "        </div>\n" +
                "    </header>" +
                "    <main>";
        String end = "</main>" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        out.println(header);
        out.println(content);
        out.println(end);
    }
}
