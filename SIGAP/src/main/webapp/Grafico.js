document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("graficoForm");
    const ctx = document.getElementById("graficoCanvas").getContext("2d");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const dataInicio = document.getElementById("dataInicio").value;
        const dataFim = document.getElementById("dataFim").value;

        fetch("/grafico", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `dataInicio=${dataInicio}&dataFim=${dataFim}`
        })
            .then(response => response.json())
            .then(data => {
                new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: Object.keys(data),
                        datasets: [{
                            label: "Quantidade",
                            data: Object.values(data),
                            backgroundColor: ["#4caf50", "#f44336"]
                        }]
                    },
                    options: {
                        scales: {
                            y: { beginAtZero: true }
                        }
                    }
                });
            })
            .catch(error => alert("Erro ao gerar gráfico: " + error.message));
    });
});
