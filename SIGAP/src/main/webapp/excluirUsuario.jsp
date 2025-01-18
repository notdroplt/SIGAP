<%@ page import="br.cefetmg.inf.sigap.service.UsuarioService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAP</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <header>
        <div class="title">
            <h1>CEFET-MG - SIGAP -</h1>
            <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
        </div>
    </header>
    <div class="sector">
  <h1>Você tem certeza que quer excluir este usuario?</h1>
  <% int id = Integer.parseInt(request.getParameter("id"));
     int authId = (int) session.getAttribute("Token");
        if (authId == id) { %>
            <h2>Você não pode excluir a si mesmo</h2>
            <a href="listarUsuarios.jsp">Voltar</a>
        <% } else { %>
          <form action="DeletarUsuario">
              <input type="hidden" name="id" value="<%= id %>">
              <input type="hidden" name="authId" value="<%= authId %>">
              <button type="submit">Excluir</button>
          </form>
        <% } %>
    </div>
</div>
</body>
</html>
