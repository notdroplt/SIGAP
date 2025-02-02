<%@ page import="br.cefetmg.inf.sigap.dto.Usuario" %>
<%@ page import="br.cefetmg.inf.sigap.service.UsuarioService" %>
<%
    Cookie[] cookies = request.getCookies();
    boolean redirecionar = false;

    if (cookies != null) {
        for (Cookie atual : cookies) {
            if (atual.getName().length() >= 10 && atual.getName().contains("dido")) {
                redirecionar = true;
                break;
            }
        }
    }

    if (redirecionar) {
        response.sendRedirect("notificacao.jsp");
        return;
    }
%>
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
        <% if(session == null || session.getAttribute("Token") == null) response.sendRedirect("index.jsp"); %>
        <div class="sector">
            <h2>Setor de Achados e Perdidos</h2>
            <p>Se você encontrou algo, clique no botão abaixo para registrar o achado.</p>
            <a href="achado.jsp" class="button">Registrar Achado</a>
        </div>
        <div class="sector">
            <h2>Setor de Achados e Perdidos</h2>
            <p>Se você perdeu algo, clique no botão abaixo para registrar a perda.</p>
            <a href="perdido.jsp" class="button">Registrar Perda</a>
        </div>
        <div class="sector">
            <h2>Setor de Achados e Perdidos</h2>
            <p>Se você deseja consultar os achados e perdidos, clique no botão abaixo.</p>
            <a href="consultar.jsp" class="button">Consultar</a>
        </div>
        <div class="sector">
            <h2>Configurações de Usuário</h2>
            <p>Se você deseja alterar suas configurações de usuário, clique no botão abaixo.</p>
            <a href="painelUsuario.jsp" class="button">Configurações</a>
        </div>
        <% try{if(UsuarioService.verificarAutoridade((int) session.getAttribute("Token"), 2)) { %>
        <div class="sector">
            <h2>Pesquisa e Edição de Usuários</h2>
            <p>Se você deseja listar e alterar usuários, clique no botão abaixo</p>
            <a href="listaUsuarios.jsp" class="button">Configurações</a>
        </div>
        <%
            }} catch (Exception e) {
            e.printStackTrace();
        %>
        <h1>Você não tem autorização para acessar essa página.</h1>
        <%
            }
        %>
    </main>
</div>
</body>
</html>
