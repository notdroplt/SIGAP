<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAA</title>
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
        <%@ page import="br.cefetmg.inf.sigap.db.Usuario" %>
        <%@ page import="br.cefetmg.inf.sigap.db.UsuarioService" %>
        <%
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
                Integer token = (Integer) sessao.getAttribute("Token");
                if (token != null) {
                    Usuario usuario = UsuarioService.getUserData(token);
                    if (usuario != null) {
                        %>
                        <h2>Bem-vindo, <%= usuario.getNome() %>!</h2>
                        <p>Email: <%= usuario.getEmail() %>
                        </p>
                        <p>CPF: <%= usuario.getCpf() %>
                        </p>
                        <%
                    } else {
                        %>
                        <p>Erro ao recuperar os dados do usuário.</p>
                        <%
                        }
                } else {
                    %>
                    <p>Token não encontrado na sessão.</p>
                    <%
                }
        } else {
        %>
        <p>Sessão não encontrada.</p>
        <%
            }
        %>
    </main>
</div>
</body>
</html>