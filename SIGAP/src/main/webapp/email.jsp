<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.mail.*, jakarta.mail.internet.*, java.util.*"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAP</title>
</head>
<body>
    <%
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        Random gerador = new Random();
        int codigo = gerador.nextInt(1000000);

        final String username = "sigap.cefetmg@gmail.com";
        final String password = "ieft hgoy ojjw htxt";

        // Configurações do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Criando a sessão de e-mail
        Session mailSession = null;
        try {
            mailSession = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                 }
            });
        }
        catch (Exception e) {
            System.out.println("Erro ao criar a sessão de e-mail: " + e.getMessage());
            response.sendRedirect("CadastroServlet");

        }
        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirmação do e-mail do " + nome);
            message.setText("Olá " + nome + ", o codigo de verificação é o seguinte: " + codigo);

            Transport.send(message);
            out.println("<p>E-mail enviado com sucesso!</p>");
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
            response.sendRedirect("CadastroServlet");
        }
        
    %>

    <form id="autoSubmit" action="processaFinal.jsp" method="POST">
        <input type="hidden" name="email" value="<%= email %>">
        <input type="hidden" name="nome" value="<%= nome %>">
        <input type="hidden" name="cpf" value="<%= cpf %>">
        <input type="hidden" name="senha" value="<%= senha %>">
        <input type="hidden" name="codigo" value="<%= codigo %>">
    </form>
    <script>
        document.getElementById('autoSubmit').submit();
    </script>
</body>
</html>