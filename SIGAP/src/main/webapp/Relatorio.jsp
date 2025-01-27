<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="relatorio.css">
    <title>Relatório de Itens</title>
</head>
<body>
<div class="container">
    <h1>Relatório de Itens</h1>
    <form method="post" action="relatorio">
        <label for="dataInicio">Data de Início:</label>
        <input type="date" id="dataInicio" name="dataInicio" required>
        <label for="dataFim">Data de Fim:</label>
        <input type="date" id="dataFim" name="dataFim" required>
        <button type="submit">Gerar Relatório</button>
    </form>
    <c:if test="${not empty itens}">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Data Perdido</th>
                <th>Data Achado</th>
                <th>Local</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${itens}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.dataPerdido}</td>
                    <td>${item.dataAchado}</td>
                    <td>${item.local}</td>
                    <td>${item.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty itens}">
        <p>Nenhum item encontrado no período selecionado.</p>
    </c:if>
</div>
</body>
</html>
