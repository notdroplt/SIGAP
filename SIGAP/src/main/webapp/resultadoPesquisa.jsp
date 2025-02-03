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
      <button id="openFiltersButton">Pesquisar</button>
      <div class="filters-box" id="filtersBox">
      <form action="Pesquisa" method="get">
          <%@ include file="item.jsp" %>
          <label for="marca">Marca:</label>
          <input type="text"  id="marca" name="valor-marca" placeholder="Digite o nome da marca..." required>
          <%@ include file="descricao.jsp" %>
          <div>
              <label for="campus-item">Campus encontrado:</label>
              <select name="campus" id="campus-item">
                  <option value="c1">Campus 1</option>
                  <option value="c2">Campus 2</option>
              </select>
          </div>
          <button type="submit" id="applyFiltersButton">Aplicar Filtros</button>
      </form>
      </div>
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
          %>
    </div>
  </main>
</div>
<script src="resultadoPesquisa.js"></script>
</body>
</html>

