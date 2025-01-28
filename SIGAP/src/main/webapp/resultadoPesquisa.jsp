<%--
  Created by IntelliJ IDEA.
  User: edjun
  Date: 17/01/2025
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetmg.inf.sigap.dto.Item" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Resultado da Pesquisa</title>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Lógica para os checkboxes de campus
            const campusCheckboxes = document.querySelectorAll('.campus-checkbox');

            campusCheckboxes.forEach(checkbox => {
                checkbox.addEventListener('change', function() {
                    if (this.checked) {
                        // Desmarca o outro campus
                        campusCheckboxes.forEach(otherCheckbox => {
                            if (otherCheckbox !== this && otherCheckbox.checked) {
                                otherCheckbox.checked = false;
                                showAlert('Você só pode selecionar um campus por vez.');
                            }
                        });
                    }
                });
            });

            // Função para exibir alertas
            function showAlert(message) {
                // Cria um elemento de aviso
                const alertDiv = document.createElement('div');
                alertDiv.className = 'alert-message';
                alertDiv.textContent = message;

                // Adiciona o aviso à página
                document.body.appendChild(alertDiv);

                // Remove o aviso após 3 segundos
                setTimeout(() => {
                    alertDiv.remove();
                }, 3000);
            }

            // Lógica para sugestões de pesquisa
            const searchInput = document.querySelector('input[name="valor"]');

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
    </script>
  <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="resultadoPesquisa.css">
</head>
<body>
<div class="container">
  <header>
    <div class="title">
      <h1>CEFET-MG - SIGAP -</h1>
      <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
    </div>
  </header>
  <div class="search-bar">
      <form action="Pesquisa" method="get">
          <input type="text" name="valor" placeholder="Digite sua pesquisa..." required>
          <div class="filtros-container">
              <label class="filtro-option">
                  <input type="checkbox" name="filtros" value="nome">
                  <span>Nome</span>
              </label>
              <label class="filtro-option">
                  <input type="checkbox" name="filtros" value="cor">
                  <span>Cor</span>
              </label>
              <label class="filtro-option">
                  <input type="checkbox" name="filtros" value="marca">
                  <span>Marca</span>
              </label>
              <label class="filtro-option">
                  <input type="checkbox" name="filtros" value="campus1" class="campus-checkbox">
                  <span>Campus 1</span>
              </label>
              <label class="filtro-option">
                  <input type="checkbox" name="filtros" value="campus2" class="campus-checkbox">
                  <span>Campus 2</span>
              </label>
          </div>
          <button type="submit">Pesquisar</button>
      </form>
  </div>
  <main>
    <div class="login-box"><h2>Resultados da Pesquisa</h2></div>
    <div class="resultados">
      <%
        List<Item> itens = (List<Item>) request.getAttribute("itensEncontrados");

        if (itens != null && !itens.isEmpty()) {
          for (Item item : itens){
            %>
              <div class="item-container">
              <span class="item-nome"><%= item.getNome() %></span>
              <a href="achado.jsp" class="button">Registrar achado</a>
              </div>
            <%
          }
        }
      else {
      %>
      <div class="resultadoNulo">
        Nenhum item com este nome encontrado.
        <button type="button" onclick="window.location.reload();">Tentar Novamente</button>
      <%
          if (itens != null && !itens.isEmpty()) {
            for (int i = 0; i<5; i++){
                for (Item item : itens){
      %>
            <div class="item-container">
              <span class="item-nome"><%= item.getNome() %></span>
            </div>
          <%
                }
            }
          }
      }
          %>
    </div>
  </main>
</div>
</body>
</html>

