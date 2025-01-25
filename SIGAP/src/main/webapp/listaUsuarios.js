document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.querySelector('button');
    const searchInput = document.querySelector('input[type="text"]');
    const categoryInputs = document.querySelectorAll('input[name="category"]');

    searchButton.addEventListener('click', function() {
        const query = searchInput.value;
        let category;
        categoryInputs.forEach(input => {
            if (input.checked) {
                category = input.value;
            }
        });
        fetchUsers(query, category);

    });
});

function fetchUsers(query, category){
    fetch(`buscaUsuarios?query=${query}&category=${category}`)
        .then(response => response.json())
        .then(data => {
            const table = document.querySelector('table');
            table.innerHTML = `
                    <tr>
                        <th style="border: black 2px solid; padding: 10px; background-color: #aaaaaa">Nome</th>
                        <th style="border: black 2px solid; padding: 10px; background-color: #aaaaaa">Email</th>
                        <th style="border: black 2px solid; padding: 10px; background-color: #aaaaaa">CPF</th>
                        <th style="border: black 2px solid; padding: 10px; background-color: #aaaaaa">Autoridade</th>
                        <th style="border: black 2px solid; padding: 10px; background-color: #aaaaaa">Editar</th>
                    </tr>
                `;
            data.forEach(usuario => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td style="border: black 2px solid; padding: 10px">${usuario.nome}</td>
                        <td style="border: black 2px solid; padding: 10px">${usuario.email}</td>
                        <td style="border: black 2px solid; padding: 10px">${usuario.cpf}</td>
                        <td style="border: black 2px solid; padding: 10px">${usuario.autoridade}</td>
                        <td style="border: black 2px solid; padding: 10px"><a href="editarUsuario.jsp?id=${usuario.id}">Editar</a></td>
                    `;
                table.appendChild(row);
            });
        })
        .catch(error => console.error('Error:', error));
}

fetchUsers("","nome");
