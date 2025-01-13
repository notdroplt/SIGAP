<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="br.cefetmg.inf.sigap.db.Item" %>
<%@ page import="br.cefetmg.inf.sigap.db.StatusItem" %>
<%@ page import="br.cefetmg.inf.sigap.db.ItemFactory" %>
<%@ page import="br.cefetmg.inf.sigap.db.Notificar" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAP</title>
</head>
<body>

    <%
        // Criar objeto "perdido" usando ItemFactory
        Item itemPerdido = ItemFactory.criarItemPerdido(1L, "Carteira", LocalDate.of(2025, 1, 10),
                "Carteira de couro marrom com documentos pessoais", "p20",
                "C2", "/imagens/carteira.jpg");

        // Criar objeto "achado" usando ItemFactory
        Item itemAchado = ItemFactory.criarItemAchado("Carteira", LocalDate.of(2025, 1, 11),
                "", "marrom com documentos dentro",
                "p20", "https://cdn-images.farfetch-contents.com/19/39/81/02/19398102_43974513_300.jpg");

        // Comparar os itens
        Boolean a = itemAchado.copare(itemPerdido);
        if(a)
        {
            Notificar.notificar(itemAchado, "Giuseppe%20Cadura", "arthur.freitas.morais@gmail.com");
        }
    %>

    <h1>Resultado da Comparação</h1>
    <p>Os itens são compatíveis? <strong><%= a ? "Sim" : "Não" %></strong></p>

</body>
</html>
