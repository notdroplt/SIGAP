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
            <a style="color: white; text-decoration: inherit;" href="home.jsp">
                <h1>CEFET-MG - SIGAP -</h1>
            </a>
            <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
            <button id="homeButton" onclick="window.location.href='home.jsp'">Home</button>
        </div>
    </header>
    <h1>Editar Usuario</h1>
    <div class="sector">
        <h2>Atualizar Dados</h2>
        <% int id = Integer.parseInt(request.getParameter("id"));
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
            <input type="password" name="senha" id="senha"><br>
            <button type="submit">Atualizar</button>
        </form>
    </div>
    <div class="sector">
        <h1>Trocar Autoridade</h1>
        <h2>Você so pode elevar pessoas com autoridade menor que a sua, e demover pessoas com autoridade menor</h2>
        <form action="TrocarAutoridade" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="authId" value="<%= authId %>">
            <label for="auth">Autoridade:</label>
            <input type="number" name="auth" id="auth" value="<%= usuario.getAutoridade() %>"><br>
            <button type="submit">Trocar</button>
        </form>
    </div>
    <div class="sector">
        <h2>Excluir Conta</h2>
        <form action="DeletarUsuario" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="authId" value="<%= authId %>">
            <button type="submit">Excluir</button>
        </form>
    </div>
    <div class="sector">
        <h2>Retornar</h2>
        <a href="listaUsuarios.jsp">Voltar</a>
    </div>
</div>
</body>
</html>
