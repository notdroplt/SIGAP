<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="Grafico.js"></script>
        <title>CEFET-MG - SIGAP</title>
    </head>
    <body>
        <header>
            <div class="title">
                <h1>CEFET-MG - SIGAP</h1>
                <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
            </div>
        </header>
        <div class="grafico">
            <div class="container">
                <h2>Gráfico de Itens Achados e Perdidos</h2>
                <form id="graficoForm">
                    <label for="dataInicio">Data de Início:</label>
                    <input type="date" id="dataInicio" name="dataInicio" required>
                    <label for="dataFim">Data de Fim:</label>
                    <input type="date" id="dataFim" name="dataFim" required>
                    <button type="submit">Gerar Gráfico</button>
                </form>
                <form action="Escolha.jsp" method="get">
                    <button type="submit" class="btn">Voltar</button>
                </form>
                <canvas id="graficoCanvas" width="400" height="200"></canvas>
            </div>
        </div>
    </body>
</html>
