package br.cefetmg.inf.sigap.backend;

import java.util.Properties;

import br.cefetmg.inf.sigap.service.ItemService;
import br.cefetmg.inf.sigap.service.UsuarioService;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.dto.Item;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class Notificar {
    public static void notificar(Item mandar)
    {
        Usuario usuario = UsuarioService.getUserData(mandar.getUid().intValue());
        String email = usuario.getEmail();
        String nome = usuario.getNome();
        final String username = "sigap.cefetmg@gmail.com";
        final String password = "ieft hgoy ojjw htxt";

        // Configurações do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Criando a sessão de e-mail
        Session mailSession = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("esse item é seu?");

            StringBuilder urlBuilder = new StringBuilder("http://localhost:8080/SIGAP/notificacao.jsp?");
            urlBuilder.append("id=").append(String.format("%09d", 123));

            if (nome != null) {
                urlBuilder.append("&nomeAluno=").append(nome.replaceAll(" ", "%20"));
            }

            if (mandar.getNome() != null) {
                urlBuilder.append("&item=").append(mandar.getNome().replaceAll(" ", "%20"));
            }

            if (((Integer) mandar.getCor()) != null) {
                urlBuilder.append("&cor=").append(ItemService.reverterCor(mandar.getCor()));
            }

            if ((mandar.getMarca()) != null) {
                urlBuilder.append("&marca=").append(mandar.getMarca().replaceAll(" ", "%20"));
            }

            if (mandar.getDescricao() != null) {
                urlBuilder.append("&descricao=").append(mandar.getDescricao().replaceAll(" ", "%20"));
            }

            if (mandar.getDataAchado() != null) {
                urlBuilder.append("&data=").append(mandar.getDataAchado());
            }

            if (mandar.getLugarAchado() != null) {
                urlBuilder.append("&local=").append(mandar.getLugarAchado().replaceAll(" ", "%20"));
            }

            if (mandar.getFoto() != null) {
                urlBuilder.append("&image=").append(mandar.getFoto());
            }
            message.setText("Achamos que esse item encontrado pode ser seu: " + urlBuilder.toString());
            Transport.send(message);

        } catch (MessagingException e) {

        }
    }
}
