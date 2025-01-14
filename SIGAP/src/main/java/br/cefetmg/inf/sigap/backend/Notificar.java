package br.cefetmg.inf.sigap.backend;

import java.util.Properties;

import br.cefetmg.inf.sigap.dto.Item;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class Notificar {
    public static void notificar(Item mandar, String nome, String email)
    {
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
            message.setSubject("esse item é seu");
            message.setText("Achamos que esse item encontrado pode ser seu: http://localhost:8080/SIGAP/notificacao.jsp?id=" + String.format("%09d", 123) + "&nomeAluno=" + nome + "&item=" + mandar.getNome() + "&descricao=" + mandar.getDescricao().replaceAll(" ", "%20")  + "&data=" + mandar.getDataAchado()+ "&local=" + mandar.getLugarAchado().replaceAll(" ", "%20") + "&image=" + mandar.getFoto());

            Transport.send(message);

        } catch (MessagingException e) {

        }
    }
}
