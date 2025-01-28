<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="grafico.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="Grafico.js"></script>
    <title>Gráfico de Itens</title>
</head>
<body>
<div class="container">
    <h1>Gráfico de Itens Achados e Perdidos</h1>
    <form id="graficoForm">
        <label for="dataInicio">Data de Início:</label>
        <input type="date" id="dataInicio" name="dataInicio" required>
        <label for="dataFim">Data de Fim:</label>
        <input type="date" id="dataFim" name="dataFim" required>
        <button type="submit">Gerar Gráfico</button>
    </form>
    <canvas id="graficoCanvas" width="400" height="200"></canvas>
</div>
</body>
</html>
