<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            font-size: 14px;
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
            <h1>CEFET-MG - SIGAP</h1>
            <span>Sistema de Gestão de Achados e Perdidos</span>
        </div>
    </header>

    <main>
        <h2>Notificação de item encontrado</h2>
        <div class="item-info">
            <div class="image-placeholder">
                <img src="placeholder.png" alt="Imagem do item">
            </div>
            <%
                String perdido[] = new String[100];
                int i = 0;

                if(request.getParameter("id") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("id"), "UTF-8");
                    perdido[i] = "0idPerdi"+ perdido[1] + "dido";
                    i+=2;
                }
                if(request.getParameter("nomeAluno") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("nomeAluno"), "UTF-8");
                    perdido[i] = "1nomeAluno"+ perdido[1] + "dido";
                    i+=2;
                }

                if(request.getParameter("item") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("item"), "UTF-8");
                    perdido[i] = "2item" + perdido[1] +"Perdido";
                    i+=2;
                }

                if(request.getParameter("cor") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("cor"), "UTF-8");
                    perdido[i] = "3cor" + perdido[1] +"Perdido";
                    i+=2;
                }

                if(request.getParameter("marca") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("marca"), "UTF-8");
                    perdido[i] = "4marca" + perdido[1] +"Perdido";
                    i+=2;
                }

                if(request.getParameter("local") != null) {
                    perdido[i+1] = java.net.URLEncoder.encode(request.getParameter("local"), "UTF-8");
                    perdido[i] = "5local" + perdido[1] +"Perdido";
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
                    String nome = "";
                    for(Cookie atual: cookies){
                        if(atual.getName().equals("1nomeAluno")){
                            nome = java.net.URLDecoder.decode(atual.getValue(), "UTF-8");
                        }
                    }
                    String buscaCookies[] = new String[100];
                    int num = 0;
                    if (cookies != null) {
                        for(int k = 0; k < cookies.length; k++) {
                            if(cookies[k].getName().endsWith("Perdido")) {
                                buscaCookies[num] = cookies[k].getName();
                                buscaCookies[num+1] = java.net.URLDecoder.decode(cookies[k].getValue(), "UTF-8");
                                num += 2;
                            }
                        }
                        if(num==0)
                            response.sendRedirect("index.jsp");
                    }
                    String idAtual = "";
                    for(int k = 0; k < cookies.length; k++) {
                            if(cookies[k].getName().endsWith("dido")) {
                                idAtual=java.net.URLDecoder.decode(cookies[k].getValue(), "UTF-8");
                                break;
                            }
                        }
                    for(int j = 0; j<num; j+=2)
                    {
                        if(buscaCookies[j].contains(idAtual))
                            out.print("<p><strong>" + buscaCookies[j].substring(1, buscaCookies[j].length() - 7 - 9) + ": </strong>" + buscaCookies[j+1] + "</p>");
                    }

                %>
            </div>
        </div>

        <p>Este item foi encontrado e bate com a descrição de um item perdido reportado por você.</p>

        <div class="actions">
            <form action="index.jsp">
                <button id="verde">Este item é meu</button>
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
                    if (cookie.getName().endsWith("dido")&&cookie.getName().contains(idAtual)) {
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