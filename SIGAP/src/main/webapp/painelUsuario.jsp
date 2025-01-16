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
        <%@ page import="br.cefetmg.inf.sigap.dto.Usuario" %>
        <%@ page import="br.cefetmg.inf.sigap.service.UsuarioService" %>
        <%@ page import="java.util.Objects" %>
        <%
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
                Integer id = (Integer) sessao.getAttribute("Token");
                if (id != null) {
                    Usuario usuario = UsuarioService.getUserData(id);
                    if (usuario != null) {
                        %>
                        <h1>Bem-vindo, <%= usuario.getNome() %>!</h1>
                        <div class="sector">
                            <fieldset>
                            <h2>Seus dados:</h2>
                            <p><strong>Nome:</strong> <%= usuario.getNome() %></p>
                            <p><strong>CPF:</strong> <%= usuario.getCpf() %></p>
                            <p><strong>Email:</strong> <%= usuario.getEmail() %></p>
                            </fieldset>
                        </div>
                        <div class="sector">
                            <fieldset>
                            <h2>Atualizar Dados</h2>
                                <% if (Objects.equals(request.getParameter("usuarioAtualizado"), "false")){  %>
                                    <p>Erro ao atualizar os dados.</p>
                                <% } else if (Objects.equals(request.getParameter("usuarioAtualizado"), "true")){ %>
                                    <p>Dados atualizados com sucesso!</p>
                                <% } %>
                            <form action="AtualizarUsuario" method="post">

                                <input type="hidden" name="id" value="<%= id %>">
                                <label for="nome">Nome:</label>
                                <input type="text" name="nome" id="nome" value="<%= usuario.getNome() %>"><br>
                                <label for="cpf">CPF:</label>
                                <input type="text" name="cpf" id="cpf" value="<%= usuario.getCpf() %>"><br>
                                <label for="email">Email:</label>
                                <input type="email" name="email" id="email" value="<%= usuario.getEmail() %>"><br>
                                <button type="submit">Atualizar</button>
                            </form>
                            </fieldset>
                        </div>
                        <div class="sector">
                            <fieldset>
                            <h2>Atualizar Senha</h2>
                            <% if (Objects.equals(request.getParameter("senhaAtualizada"), "false")){  %>
                            <p>Erro ao atualizar os dados.</p>
                            <% } else if (Objects.equals(request.getParameter("senhaAtualizada"), "true")){ %>
                            <p>Dados atualizados com sucesso!</p>
                            <% } %>
                            <form action="AtualizarSenha" method="post">

                                <input type="hidden" name="id" value="<%= id %>">
                                <label for="senha">Nova Senha:</label>
                                <input type="password" name="senha" id="senha"><br>
                                <label for="senhaold">Senha Atual:</label>
                                <input type="password" name="senhaOld" id="senhaold"><br>
                                <button type="submit">Atualizar</button>
                            </form>
                            </fieldset>
                        </div>
                        <div class="sector">
                            <fieldset>
                            <h2>Log Out</h2>
                                <form action="Logout" method="post">
                                    <button type="submit">Log Out</button>
                                </form>

                            </fieldset>
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