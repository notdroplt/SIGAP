/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById('relatorioForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const dataInicio = document.getElementById('dataInicio').value;
    const dataFim = document.getElementById('dataFim').value;

    try {
        const response = await fetch('/relatorio', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `dataInicio=${dataInicio}&dataFim=${dataFim}`
        });

        if (response.ok) {
            const reportData = await response.json();
            localStorage.setItem('reportData', JSON.stringify(reportData));
            localStorage.setItem('intervalo', `${dataInicio} a ${dataFim}`);
            window.location.href = 'relatorio.html';
        } else {
            alert('Erro ao gerar o relatório.');
        }
    } catch (error) {
        console.error(error);
    }
});

if (window.location.pathname.endsWith('relatorio.html')) {
    const intervalo = localStorage.getItem('intervalo');
    const reportData = JSON.parse(localStorage.getItem('reportData'));

    document.getElementById('intervalo').textContent = intervalo;

    const conteudo = document.getElementById('relatorioConteudo');
    conteudo.innerHTML = JSON.stringify(reportData, null, 2);
}

