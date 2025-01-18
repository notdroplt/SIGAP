<%--
  Created by IntelliJ IDEA.
  User: edjun
  Date: 17/01/2025
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetmg.inf.sigap.db.Item" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Resultado da Pesquisa</title>
  <link rel="stylesheet" href="style.css">
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
          <select name="filtro">
              <option value="nome">Nome</option>
              <option value="cor">Cor</option>
              <option value="marca">Marca</option>
          </select>
          <input type="text" name="valor" placeholder="Digite sua pesquisa..." required>
          <button type="submit">Pesquisar</button>
      </form>
  </div>

  <main>
    <div class="login-box"><h2>Resultados da Pesquisa</h2></div>
    <div class="resultados">

      <%
        // Obtém a lista de itens da requisição
        @SuppressWarnings("unchecked")
        List<Item> itens = (List<Item>) request.getAttribute("itensEncontrados");

        if (itens != null && !itens.isEmpty()) {
          for (Item item : itens){
            %>
              <div class="acoes">
              <span><%= item.getNome() %></span>
              <input type="button" value="Adicionar">
              </div>
            <%
          }
        }
      else {
      %>
      <div class="resultadoNulo">
        Nenhum item com este nome encontrado.
        <button type="button" onclick="window.location.reload();">Tentar Novamente</button>
      </div>
      <%
        }
      %>
    </div>
  </main>
</div>
</body>
</html>

