<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Requisição de Relatório</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Preencha os campos abaixo para determinar o intervalo de tempo que o relatório irá abranger:</h1>
        <form id="relatorioForm">
            <label for="dataInicio">Data de início:</label>
            <input type="date" id="dataInicio" name="dataInicio" required>
            <label for="dataFim">Data de término:</label>
            <input type="date" id="dataFim" name="dataFim" required>
            <button type="submit">Gerar Relatório</button>
        </form>
    </div>
    <script src="scripts.js"></script>
</body>
</html>
