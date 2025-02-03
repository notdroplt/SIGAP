document.addEventListener('DOMContentLoaded', function() {
    const openFiltersButton = document.getElementById('openFiltersButton');
    const filtersBox = document.getElementById('filtersBox');
    const applyFiltersButton = document.getElementById('applyFiltersButton');
    const itemSelect = document.getElementById('nome-item');
    const descSelect = document.getElementById('desc-item');
    const campusSelect = document.getElementById('campus-item');
    const marcaSelect = document.getElementById("marca");

    if (!openFiltersButton || !filtersBox || !applyFiltersButton || !itemSelect || !descSelect || !campusSelect) {
        console.warn("Algum elemento do menu de filtros não foi encontrado.");
        return; // Para a execução se faltar algum elemento
    }

    // Abrir/Fechar o menu de filtros
    openFiltersButton.addEventListener('click', function() {
        console.log('Botão clicado!'); // Debugging
        filtersBox.style.display = (filtersBox.style.display === 'none' || filtersBox.style.display === '') ? 'block' : 'none';
    });

    // Aplicar filtros e redirecionar
    applyFiltersButton.addEventListener('click', function(event) {
        event.preventDefault(); // Evita o envio do formulário para depuração
        console.log('Aplicar Filtros clicado!'); // Debugging

        const item = itemSelect.value;
        const desc = Array.from(descSelect.selectedOptions).map(option => option.value).join(',');
        const campus = campusSelect.value;
        const marca = marcaSelect.value;

        let url = '/sua-aplicacao/Pesquisa?';
        if (item) url += `item=${encodeURIComponent(item)}&`;
        if (desc) url += `desc=${encodeURIComponent(desc)}&`;
        if (campus) url += `campus=${encodeURIComponent(campus)}&`;
        if (marca) url += `marca=${encodeURIComponent(marca)}&`;

        url = url.replace(/[&?]$/, ''); // Remove último "&" ou "?" se necessário

        console.log('URL gerada:', url); // Debugging
        window.location.href = url;
    });

    const searchInput = document.querySelector('input[name="valor-marca"]');

    // Função para recuperar cookies
    function getCookies() {
        const cookies = document.cookie.split(';');
        const searchSuggestions = [];

        cookies.forEach(cookie => {
            const [name, value] = cookie.trim().split('=');
            if (name.startsWith('pesquisa_')) {
                searchSuggestions.push(value);
            }
        });

        return searchSuggestions;
    }

    // Recuperar sugestões de pesquisa dos cookies
    const searchSuggestions = getCookies();

    if (searchSuggestions.length > 0) {
        // Criar o elemento <datalist>
        const datalist = document.createElement('datalist');
        datalist.id = 'searchSuggestions';

        // Adicionar cada sugestão como uma <option>
        searchSuggestions.forEach(suggestion => {
            const option = document.createElement('option');
            option.value = suggestion;
            datalist.appendChild(option);
        });

        // Associar o <datalist> ao campo de pesquisa
        searchInput.setAttribute('list', 'searchSuggestions');
        document.body.appendChild(datalist);
    }

    // Exibir sugestões ao clicar na barra de pesquisa
    searchInput.addEventListener('focus', function() {
        if (this.getAttribute('list') === 'searchSuggestions') {
            this.click(); // Força a exibição das sugestões
        }
    });
});