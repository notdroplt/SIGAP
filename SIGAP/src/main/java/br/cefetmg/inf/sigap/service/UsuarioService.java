package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.dao.UsuarioDao;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioService {
    public static String extrairCpf(String cpfRawString) throws NumberFormatException{
        System.out.println("CPF RAW STRING: " + cpfRawString);
        StringBuilder cpfString = new StringBuilder();
        String digits = cpfRawString.replaceAll("\\D", "");
        cpfString.append(digits);
        System.out.println("CPF STRING: " + cpfString);
        return cpfString.toString();
    }
    public static boolean criarUsuario(Usuario usuario){
        return UsuarioDao.adicionarUsuario(usuario);
    }
    public static boolean login(String cpf, byte[] senha){
        return UsuarioDao.VerificarUsuario(cpf, senha);
    }
    public static boolean verificarUsuario(int id, byte[] senha){
        return UsuarioDao.VerificarUsuario(id, senha);
    }
    public static int getId(String cpf, byte[] senha){
        return UsuarioDao.getId(cpf, senha);
    }
    public static Usuario getUserData(int id){
        return UsuarioDao.getUserData(id);
    }
    public static Usuario[] listarUsuarios(){return UsuarioDao.listarUsuarios();}
    public static Usuario[] listarUsuarios(String query, String category){return UsuarioDao.listarUsuarios(query, category);}
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
    public static boolean validarCpf(String cpf){

        String cpfString = String.valueOf(cpf);
        if(cpfString.length() != 11)
            return false;

        int[] cpfArray = new int[11];
        for(int i = 0; i < 11; i++)
            cpfArray[i] = Integer.parseInt(String.valueOf(cpfString.charAt(i)));

        int sum = 0;
        for(int i = 1; i <= 9; i++)
            sum += cpfArray[i-1] * i;

        int remainder = sum % 11;
        remainder = remainder==10 ? 0 : remainder;
        if (remainder != cpfArray[9])
            return false;

        sum = 0;
        for(int i = 0; i <= 9; i++)
            sum += cpfArray[i] * i;

        remainder = sum % 11;
        remainder = remainder==10 ? 0 : remainder;
        if (remainder != cpfArray[10])
            return false;
        return true;
    }
}
