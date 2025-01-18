<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%
    Cookie[] cookies = request.getCookies();
    boolean redirecionar = false;

    if (cookies != null) {
        for (Cookie atual : cookies) {
            if (atual.getName().length() >= 10 && atual.getName().substring(0, 10).equals("1nomeAluno")) {
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
            <h1>CEFET-MG - SIGAP -</h1>
            <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
        </div>

    </header>

    <main>
        <div class="alert">
            <p><strong>ATENÇÃO!</strong><br>O seu login é o seu CPF e sua senha é a mesma cadastrada na <a href="#">Identificação Única</a>.<br>O sistema diferencia letras maiúsculas de minúsculas na senha.</p>

        </div>


        <div class="login-box">
            <h2>Entrar no Sistema</h2>
            <form action="LoginServlet" method="POST">
                <label for="usuario">Usuário:</label>
                <input type="text" id="usuario" name="cpf" placeholder="CPF">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" placeholder="Senha">
                <button type="submit">Entrar</button>
            </form>
        </div>

        <div class="help-links">
            <p><a href="Cadastro.jsp">Cadastre-se</a> | <a href="#">Esqueci minha senha</a></p>

        </div>
    </main>
</div>
</body>
</html>
