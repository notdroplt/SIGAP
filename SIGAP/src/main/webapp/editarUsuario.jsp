<%@ page import="java.util.Objects" %>
<%@ page import="br.cefetmg.inf.sigap.dto.Usuario" %>
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
<h1>Editar Usuario</h1>
<div class="sector">
    <h2>Atualizar Dados</h2>
    <%  int id = Integer.parseInt(request.getParameter("id"));
        int authId = (int) session.getAttribute("Token");
        Usuario usuario = UsuarioService.getUserData(id);%>
    <form action="EditarUsuario" method="post">

        <input type="hidden" name="id" value="<%= id %>">
        <input type="hidden" name="authId" value="<%= authId %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= usuario.getNome() %>"><br>

        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" value="<%= usuario.getCpf() %>"><br>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="<%= usuario.getEmail() %>"><br>

        <label for="senha">Senha:</label>
        <input type="password" name="senha" id="senha" value="<%= usuario.getSenha() %>"><br>
        <button type="submit">Atualizar</button>
    </form>
</div>
</div>

</body>
</html>
