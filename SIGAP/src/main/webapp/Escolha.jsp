<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="relatorio.css">
    <title>Sigap - Escolha de Relatório</title>
</head>
<body>
<div class="container">
    <h1>Escolha a Opção Desejada</h1>
    <div class="buttons">
        <form action="Relatorio.jsp" method="get">
            <button type="submit" class="btn">Relatório de Itens</button>
        </form>
        <form action="Grafico.jsp" method="get">
            <button type="submit" class="btn">Gráfico de Itens</button>
        </form>
    </div>
</div>
</body>
</html>
