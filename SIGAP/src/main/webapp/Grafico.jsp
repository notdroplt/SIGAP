<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.time.LocalDate, java.util.HashMap, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <title>CEFET-MG - SIGAP - Gráfico de Itens</title>
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
            <form method="post">
                <label for="dataInicio">Data de Início:</label>
                <input type="date" id="dataInicio" name="dataInicio" required>
                <label for="dataFim">Data de Fim:</label>
                <input type="date" id="dataFim" name="dataFim" required>
                <button type="submit">Gerar Gráfico</button>
            </form>
            <form action="Escolha.jsp" method="get">
                <button type="submit" class="btn">Voltar</button>
            </form>
            <%
                // Inicializa as variáveis para os totais
                int totalAchados = 0;
                int totalPerdidos = 0;
                String dataInicioStr = request.getParameter("dataInicio");
                String dataFimStr = request.getParameter("dataFim");

                if(dataInicioStr != null && dataFimStr != null) {
                    try {
                        LocalDate dataInicio = LocalDate.parse(dataInicioStr);
                        LocalDate dataFim = LocalDate.parse(dataFimStr);

                        // Configuração da conexão com o banco
                        String dbUrl = "jdbc:postgresql://db:5432/sigap";
                        String dbUser = "sigap";
                        String dbPassword = "sigap";

                        Class.forName("org.postgresql.Driver");
                        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

                        // Consulta para itens achados
                        String sqlAchados = "SELECT COUNT(*) AS total FROM Item WHERE data_achado BETWEEN ? AND ?";
                        PreparedStatement stmtAchados = conn.prepareStatement(sqlAchados);
                        stmtAchados.setDate(1, Date.valueOf(dataInicio));
                        stmtAchados.setDate(2, Date.valueOf(dataFim));
                        ResultSet rsAchados = stmtAchados.executeQuery();
                        if(rsAchados.next()){
                            totalAchados = rsAchados.getInt("total");
                        }
                        rsAchados.close();
                        stmtAchados.close();

                        // Consulta para itens perdidos
                        String sqlPerdidos = "SELECT COUNT(*) AS total FROM Item WHERE data_perdido BETWEEN ? AND ?";
                        PreparedStatement stmtPerdidos = conn.prepareStatement(sqlPerdidos);
                        stmtPerdidos.setDate(1, Date.valueOf(dataInicio));
                        stmtPerdidos.setDate(2, Date.valueOf(dataFim));
                        ResultSet rsPerdidos = stmtPerdidos.executeQuery();
                        if(rsPerdidos.next()){
                            totalPerdidos = rsPerdidos.getInt("total");
                        }
                        rsPerdidos.close();
                        stmtPerdidos.close();

                        conn.close();
                    } catch(Exception e) {
                        out.println("<p style='color:red;'>Erro ao consultar o banco: " + e.getMessage() + "</p>");
                    }
                }
            %>
            <canvas id="graficoCanvas" width="400" height="200"></canvas>
            <script>
                // Prepara os dados para o gráfico
                const totalAchados = <%= totalAchados %>;
                const totalPerdidos = <%= totalPerdidos %>;
                
                const ctx = document.getElementById("graficoCanvas").getContext("2d");
                new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: ["Achados", "Perdidos"],
                        datasets: [{
                            label: "Quantidade de Itens",
                            data: [totalAchados, totalPerdidos],
                            backgroundColor: ["#4caf50", "#f44336"]
                        }]
                    },
                    options: {
                        scales: {
                            y: { beginAtZero: true }
                        }
                    }
                });
            </script>
        </div>
    </div>
</body>
</html>
