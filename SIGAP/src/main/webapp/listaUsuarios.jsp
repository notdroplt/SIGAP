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
                <th>Telefone</th>
                <th>Matricula</th>
                <th>Curso</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <% for(Usuario usuario : UsuarioService.listarUsuarios()) { %>
                <tr class="sector">
                    <td><%= usuario.getNome() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td><%= usuario.getCpf() %></td>
                    <td><a href="editarUsuario.jsp?id=<%= usuario.getId() %>">Editar</a></td>
                    <td><a href="excluirUsuario.jsp?id=<%= usuario.getId() %>">Excluir</a></td>
                </tr>
            <% } %>
        </table>
    </main>
</div>
</body>
</html>