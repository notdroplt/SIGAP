<%@ page import="br.cefetmg.inf.sigap.dto.Usuario" %>
<%@ page import="br.cefetmg.inf.sigap.service.UsuarioService" %>
<%@ page import="java.util.Objects" %>
<html lang="pt-br">
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
        </div>

    </header>

    <main>
        <%  int id = (int) session.getAttribute("Token");
            if(!UsuarioService.verificarAutoridade(id, 2)
                response.sendRedirect("home.jsp");%>
        <div class="sector">
            <label>
                <h1>Buscar Usuário:</h1>
                <input id="searchInput"type="text">
            </label>
            <label>
                <button id="searchButton">Buscar</button>
            </label><br>
            <label>
                <input type="radio" name="category" value="nome" checked class="category">Nome
            </label>
            <label>
                <input type="radio" name="category" value="email" class="category">Email
            </label>
            <label>
                <input type="radio" name="category" value="cpf" class="category">CPF
            </label>
            <h1>Usuarios Cadastrados</h1>
        <table style="margin: 0px auto; border-collapse: collapse;"></table>
        </div>
    </main>
</div>
<script src="listaUsuarios.js"></script>
</body>
</html>