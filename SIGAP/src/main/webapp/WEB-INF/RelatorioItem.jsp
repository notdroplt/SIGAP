<%-- 
    Document   : RelatorioItem
    Created on : 7 de jan de 2025, 21:44:18
    Author     : luisg
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Relatório de Itens</title>
    <link rel="stylesheet" href="../css/relatorio.css">
</head>
<body>
    <h1>Relatório de Itens</h1>
    <form action="/relatorio" method="get">
        <label for="dataInicio">Data de Início:</label>
        <input type="date" id="dataInicio" name="dataInicio" required>
        <label for="dataFim">Data de Fim:</label>
        <input type="date" id="dataFim" name="dataFim" required>
        <button type="submit">Filtrar</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Data</th>
                <th>Local</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${itens}">
            <tr>
                <td>${item.nome}</td>
                <td>${item.descricao}</td>
                <td>${item.data}</td>
                <td>${item.local}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>


