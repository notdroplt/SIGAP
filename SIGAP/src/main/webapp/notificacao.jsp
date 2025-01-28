<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.cefetmg.inf.sigap.dto.Item" %>
<%@ page import="br.cefetmg.inf.sigap.service.ItemService" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAP</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            line-height: 1.6;
        }

        .container {
            background-color: white;
            border: 1px solid #ddd;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
        }

        header {
            background-color: #173a7a;
            color: white;
            text-align: center;
            padding: 20px 10px;
        }

        header .title h1 {
            font-size: 22px;
            margin-bottom: 5px;
        }

        header .title span {
            font-size: 14px;
        }

        main {
            padding: 20px;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 20px;
            color: #173a7a;
        }

        .item-info {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .image-placeholder {
            width: 80px;
            height: 80px;
            background-color: #eaeaea;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        img {
            width: 80px;
            height: 80px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
            border-radius: 5px;
        }

        .details p {
            margin: 5px 0;
            font-size: 14px;
            color: #555;
        }

        .actions {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .actions button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: px;
            font-weight: bold;
        }

        .actions #verde{
            background-color: green;
            color: white;
        }

        .actions #vermelho{
            background-color: red;
            color: white;
        }

        .actions button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <div class="title">
            <a style="color: white; text-decoration: inherit;" href="home.jsp">
                <h1>CEFET-MG - SIGAP -</h1>
            </a>
            <span>Sistema de Gestão de Achados e Perdidos</span>
        </div>
    </header>

    <main>
        <h2>Notificação de item encontrado</h2>
        <div class="item-info">
            <%
            String id = request.getParameter("id");
                String perdido[] = new String[100];
                int i = 0;

                if(request.getParameter("id") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("id"), "UTF-8");
                    perdido[i] = "0idPerdi"+ perdido[1] + "dido";
                    i+=2;
                }


                for(int j = 0; j < i; j+=2) {
                    Cookie cookie = new Cookie(perdido[j], perdido[j+1]);
                    cookie.setMaxAge(60 * 60 * 24 * 90);
                    response.addCookie(cookie);
                }


            %>

            <div class="details">
                <%
                    Cookie[] cookies = request.getCookies();
                    for(Cookie atual: cookies){
                        if(atual.getName().endsWith("dido")){
                            id = java.net.URLDecoder.decode(atual.getValue(), "UTF-8");
                            break;
                        }
                    }
                    ItemService service = ItemService.getInstance();
                    Long itemId = Long.parseLong(id);
                    List<Item> itens = service.getItemPorId(itemId);
                    Item mandar = null;
                    if (itens != null && !itens.isEmpty())
                        mandar = itens.get(0);
                    else
                        out.print(itemId);

                    String buscaCookies[] = new String[100];
                    i=0;
                    if (mandar.getNome() != null) {
                        buscaCookies[i]="Item";
                        buscaCookies[i+1]=mandar.getNome();
                        i+=2;
                    }

                    if (((Integer) mandar.getCor()) != null) {
                        buscaCookies[i]="Cor";
                        buscaCookies[i+1]=ItemService.reverterCor(mandar.getCor());
                        i+=2;
                    }

                    if ((mandar.getMarca()) != null) {
                        buscaCookies[i]="Marca";
                        buscaCookies[i+1]=mandar.getMarca();
                        i+=2;}

                    if (mandar.getDescricao() != null) {
                        buscaCookies[i]="Descrição";
                        buscaCookies[i+1]=mandar.getDescricao();
                        i+=2;}

                    if (mandar.getDataAchado() != null) {
                        buscaCookies[i]="Data";
                        buscaCookies[i+1]=mandar.getDataAchado().toString();;
                        i+=2;}

                    if (mandar.getLugarAchado() != null) {
                        buscaCookies[i]="Lugar";
                        buscaCookies[i+1]=mandar.getLugarAchado();
                        i+=2;}

                    if (mandar.getFoto() != null) {
                        buscaCookies[i]="Foto";
                        buscaCookies[i+1]=mandar.getFoto();
                        i+=2;}
                    for(int j = 0; j < i-2; j+=2)
                    {
                        if(buscaCookies[j].contains("Foto")){
                            out.print("<div><img src=" + buscaCookies[j+1] +" alt='Imagem do item'></div>");
                            out.print("AAAAAAAAAAAAA");
                        }
                        else if(buscaCookies[j].contains("Cor"))
                            out.print("<p><strong>" + buscaCookies[j] + ": </strong>" + "<div style='width: 60px; height: 20px; background-color: " + buscaCookies[j+1] + ";'></div>");
                        else
                            out.print("<p><strong>" + buscaCookies[j] + ": </strong>" + buscaCookies[j+1] + "</p>");
                    }

                %>
            </div>
        </div>

        <p>Este item foi encontrado e bate com a descrição de um item perdido reportado por você.</p>

        <div class="actions">
            <form action="index.jsp">
                <button id="verde" type="submit" name="acao" value="apagar">Este item é meu</button>
            </form>
            <form method="POST">
                <button id="vermelho" type="submit" name="acao" value="apagar">Este item não é meu</button>
            </form>

        </div>
    </main>
    <%

        if ("POST".equalsIgnoreCase(request.getMethod()) && "apagar".equals(request.getParameter("acao"))) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().endsWith("dido")&&cookie.getName().contains(id)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            response.sendRedirect("index.jsp");
        }
    %>
</div>
</body>
</html>