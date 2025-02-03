<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.cefetmg.inf.sigap.dto.Item" %>
<%@ page import="br.cefetmg.inf.sigap.service.ItemService" %>
<%@ page import="br.cefetmg.inf.sigap.service.ImagemService" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.Cookie"%>
<%@ page import="jakarta.servlet.*" %>

<%
if ("POST".equalsIgnoreCase(request.getMethod()) && "apagar".equals(request.getParameter("acao"))) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        int uni= 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().endsWith("dido")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                uni = 1;
                response.sendRedirect("home.jsp");
                return;
            }
        }

    }
}
%>

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
        #homeButton {
            top: 20px;   
            right: 20px; 
            width: 50px;
            height: 50px;
            border: none;
            background-color: #fff; 
            color: #000;           
            border-radius: 50%;     
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0,0,0,0.3); 
            font-weight: bold;
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
                    String[] itensArray = new String[30];
                    itensArray[1] = "Lápis";
                    itensArray[2] = "Borracha";
                    itensArray[3] = "Apontador";
                    itensArray[4] = "Lapiseira";
                    itensArray[5] = "Caneta";
                    itensArray[6] = "Marca Texto";
                    itensArray[7] = "Caderno";
                    itensArray[8] = "Livro";
                    itensArray[9] = "Apostila";
                    itensArray[10] = "Mochila";
                    itensArray[11] = "Estojos";
                    itensArray[12] = "Garrafa";
                    itensArray[13] = "Roupa";
                    itensArray[14] = "Chaves";
                    itensArray[15] = "Óculos";
                    itensArray[16] = "Celular";
                    itensArray[17] = "Tablet";
                    itensArray[18] = "Laptop";
                    itensArray[19] = "Fones de ouvido";
                    itensArray[20] = "Carregador";
                    itensArray[21] = "Mouse";
                    itensArray[22] = "Bola";
                    itensArray[23] = "Peteca";
                    itensArray[24] = "";
                    itensArray[25] = "Cartas de jogo";
                    itensArray[26] = "Identidade";
                    itensArray[27] = "Cartão de estudante";
                    itensArray[28] = "Atestado médico";
                    itensArray[29] = "Documentos pessoais";

                    String[] descricao = new String[21];
                    descricao[1] = "Novo";
                    descricao[2] = "Usado";
                    descricao[3] = "Danificado";
                    descricao[4] = "Limpo";
                    descricao[5] = "Sujo";
                    descricao[6] = "Pequeno";
                    descricao[7] = "Grande";
                    descricao[8] = "Colorido";
                    descricao[9] = "Desgastado";
                    descricao[10] = "Valioso";
                    descricao[11] = "Comum";
                    descricao[12] = "Incomum";
                    descricao[13] = "Raro";
                    descricao[14] = "Épico";
                    descricao[15] = "Lendário";
                    descricao[16] = "Mítico";
                    descricao[17] = "Funcional";
                    descricao[18] = "Quebrado";
                    descricao[19] = "Antigo";
                    descricao[20] = "Moderno";

                    final String[] lugares = new String[32];
                    lugares[1] = "Portaria Rua Alpes";
                    lugares[2] = "Campão";
                    lugares[3] = "Quadra Poliesportiva";
                    lugares[4] = "Quadra Fechada";
                    lugares[5] = "Quadra de vôlei";
                    lugares[6] = "1º andar";
                    lugares[7] = "2º andar";
                    lugares[8] = "3º andar";
                    lugares[9] = "4º andar";
                    lugares[10] = "Piscina do 5º andar";
                    lugares[11] = "Lanchonete C1";
                    lugares[12] = "Biblioteca C1";
                    lugares[13] = "Restaurante C1";
                    lugares[14] = "Estacionamento C1";
                    lugares[15] = "Portaria Av. Amazonas C1";

                    lugares[16] = "Portaria Av. Amazonas C2";
                    lugares[17] = "Estacionamento C2 (ao lado da portaria)";
                    lugares[18] = "Oca (de baixo)";
                    lugares[19] = "Oca (de cima)";
                    lugares[20] = "P1 (Prédio principal)";
                    lugares[21] = "P2 (Restaurante)";
                    lugares[22] = "P4 (mecânica 1)";
                    lugares[23] = "P5 (mecânica 2)";
                    lugares[24] = "P6/P7 (biblioteca)";
                    lugares[25] = "P8/P9 (NEAC)";
                    lugares[26] = "Praça C2";
                    lugares[27] = "P12 (EDI/Engenharia Civil)";
                    lugares[28] = "P17 (DECOM/Engenharia da Computação)";
                    lugares[29] = "P18";
                    lugares[30] = "P19 (ELE/ELT)";
                    lugares[31] = "P20";


                    Cookie[] cookies = request.getCookies();
                    for(Cookie atual: cookies){
                        if(atual.getName().endsWith("dido")){
                            id = java.net.URLDecoder.decode(atual.getValue(), "UTF-8");
                            System.out.println("aaaaaaaa" + id);
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
                        buscaCookies[i+1]=itensArray[Integer.parseInt(mandar.getNome())];
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
                        buscaCookies[i+1]=descricao[Integer.parseInt(mandar.getDescricao())];
                        i+=2;}

                    if (mandar.getDataAchado() != null) {
                        buscaCookies[i]="Data";
                        buscaCookies[i+1]=mandar.getDataAchado().toString();
                        i+=2;}

                    if (mandar.getLugarAchado() != null) {
                        buscaCookies[i]="Lugar";
                        buscaCookies[i+1]=lugares[Integer.parseInt(mandar.getLugarAchado())];
                        i+=2;}

                    if (mandar.getFoto() != null) {
                        buscaCookies[i]="Foto";
                        ImagemService iservice = ImagemService.getInstance();


                        out.print("<div><img src=" + iservice.recuperarImagem(mandar.getFoto()) +" alt='Imagem do item'></div>");
                        buscaCookies[i+1]=iservice.recuperarImagem(mandar.getFoto());
                        i+=2;}
                    else
                        out.print(mandar.getFoto());
                    for(int j = 0; j < i-2; j+=2)
                    {
                        if(buscaCookies[j].contains("Foto")){
                            out.print("aa");

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
            <form method="POST">
                <button id="verde" type="submit" name="acao" value="apagar">Este item é meu</button>
            </form>
            <form method="POST">
                <button id="vermelho" type="submit" name="acao" value="apagar">Este item não é meu</button>
            </form>

        </div>
    </main>

</div>
</body>
</html>