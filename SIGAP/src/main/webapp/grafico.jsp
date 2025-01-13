<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gráficos de Incidências</title>
        <link rel="stylesheet" href="grafico.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <header>
            <h1>Gráficos de Incidências</h1>
        </header>

        <main>
            <section id="grafico-achados">
                <h2>Itens Achados</h2>
                <canvas id="graficoAchados"></canvas>
            </section>
            <section id="grafico-perdidos">
                <h2>Itens Perdidos</h2>
                <canvas id="graficoPerdidos"></canvas>
            </section>
        </main>

        <%
            String urlAchados = request.getContextPath() + "/grafico?tipo=achados";
            String urlPerdidos = request.getContextPath() + "/grafico?tipo=perdidos";
        %>

        <script>
            async function carregarDados(tipo) {
                const url = tipo === "achados" ? "<%= urlAchados %>" : "<%= urlPerdidos %>";
                const response = await fetch(url);
                if (response.ok) {
                    return await response.json();
                } else {
                    console.error(`Erro ao carregar dados de ${tipo}`);
                    return {};
                }
            }

            async function renderizarGraficos() {
                const dadosAchados = await carregarDados("achados");
                const dadosPerdidos = await carregarDados("perdidos");

                const ctxAchados = document.getElementById("graficoAchados").getContext("2d");
                const ctxPerdidos = document.getElementById("graficoPerdidos").getContext("2d");

                new Chart(ctxAchados, {
                    type: "bar",
                    data: {
                        labels: Object.keys(dadosAchados),
                        datasets: [{
                                label: "Achados",
                                data: Object.values(dadosAchados),
                                backgroundColor: "rgba(54, 162, 235, 0.7)",
                            }],
                    },
                });

                new Chart(ctxPerdidos, {
                    type: "bar",
                    data: {
                        labels: Object.keys(dadosPerdidos),
                        datasets: [{
                                label: "Perdidos",
                                data: Object.values(dadosPerdidos),
                                backgroundColor: "rgba(255, 99, 132, 0.7)",
                            }],
                    },
                });
            }

            renderizarGraficos();
        </script>
    </body>
</html>

