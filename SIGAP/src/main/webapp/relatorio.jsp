<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map, java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relatório</title>
    <link rel="stylesheet" href="relatorio.css">
</head>
<body>
    <header>
        <h1>Relatório - <%= request.getAttribute("tipoRelatorio") != null ? request.getAttribute("tipoRelatorio") : "Nenhum Relatório" %></h1>
    </header>
    <main>
        <%
            Map<String, Integer> dados = (Map<String, Integer>) request.getAttribute("dados");
            List<br.cefetmg.inf.sigap.db.Item> itens = (List<br.cefetmg.inf.sigap.db.Item>) request.getAttribute("itens");
        %>

        <% if (dados != null) { %>
            <table>
                <thead>
                    <tr>
                        <th>Categoria</th>
                        <th>Quantidade</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map.Entry<String, Integer> entry : dados.entrySet()) { %>
                        <tr>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else if (itens != null) { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Local</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (br.cefetmg.inf.sigap.db.Item item : itens) { %>
                        <tr>
                            <td><%= item.getId() %></td>
                            <td><%= item.getNome() %></td>
                            <td><%= item.getLocal() %></td>
                            <td><%= item.getStatus() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>Nenhum dado disponível para exibição.</p>
        <% } %>
    </main>
</body>
</html>
