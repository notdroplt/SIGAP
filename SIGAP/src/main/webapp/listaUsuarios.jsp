<%@ page import="br.cefetmg.inf.sigap.dto.Usuario" %>
<%@ page import="br.cefetmg.inf.sigap.service.UsuarioService" %>
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
        <h1>Usuarios Cadastrados</h1>
        <table>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Autoridade</th>
                <th>Editar</th>
            </tr>
            <% for(Usuario usuario : UsuarioService.listarUsuarios()) { %>
                <tr class="sector">
                    <td><%= usuario.getNome() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td><%= usuario.getCpf() %></td>
                    <td><%= usuario.getAutoridade()%></td>
                    <td><a href="editarUsuario.jsp?id=<%= usuario.getId() %>">Editar</a></td>
                </tr>
            <% } %>
        </table>
    </main>
</div>
</body>
</html>