<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.mail.*, jakarta.mail.internet.*, java.util.*"%>
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
            padding-bottom: 20px;
            border-radius: 5px;
        }

        main {
            margin-left: 20px;
            margin-top: 10px
        }

        header {
            background-color: #173a7a;
            color: white;
            text-align: center;
            padding: 20px 10px;
            border-radius: 5px 5px 0 0;
        }

        header h1 {
            font-size: 22px;
        }

        header span {
            font-size: 14px;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 20px;
            color: #173a7a;
        }

        .actions {
            display: flex;
            justify-content: center;
            gap: 10px;
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

        .actions #verde {
            background-color: green;
            color: white;
        }

        .actions #vermelho {
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
        <a style="color: white; text-decoration: inherit;" href="home.jsp">
            <h1>CEFET-MG - SIGAP -</h1>
        </a>
        <span>Verificação de e-mail</span>
    </header>

    <main>
    <%
    HttpSession sessionObj = request.getSession();

                    String email = request.getParameter("email");
                    String nome = request.getParameter("nome");
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    String codigo = request.getParameter("codigo");
                    Integer id = null;
                    String idS = request.getParameter("id");
                    if(request.getParameter("id")!=null)
                        try {
                            id = Integer.parseInt(idS);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                    String teste = request.getParameter("teste");

                    if (email != null) sessionObj.setAttribute("email", email);
                    if (nome != null) sessionObj.setAttribute("nome", nome);
                    if (cpf != null) sessionObj.setAttribute("cpf", cpf);
                    if (senha != null) sessionObj.setAttribute("senha", senha);
                    if (codigo != null) sessionObj.setAttribute("codigo", codigo);
                    if (id != null) sessionObj.setAttribute("id", id);
                    if (teste != null) sessionObj.setAttribute("teste", teste);

                    email = (String) sessionObj.getAttribute("email");
                    nome = (String) sessionObj.getAttribute("nome");
                    cpf = (String) sessionObj.getAttribute("cpf");
                    senha = (String) sessionObj.getAttribute("senha");
                    codigo = (String) sessionObj.getAttribute("codigo");
                    if(sessionObj.getAttribute("id")!=null )
                        id = (Integer) sessionObj.getAttribute("id");
                    teste = (String) sessionObj.getAttribute("teste");
    %>
        <h2>Confirmação de Código</h2>
        <form method="POST">
            <input type="text" id="codigoR" name="codigoR" placeholder="Digite o código">
            <button type="submit" name="acao" value="apagar">Confirmar</button>
        </form>
        <form action='email.jsp' method='POST'>
                <input type="hidden" name="email" value="<%= email %>">
                <input type="hidden" name="nome" value="<%= nome %>">
                <input type="hidden" name="cpf" value="<%= cpf %>">
                <input type="hidden" name="senha" value="<%= senha %>">
                <input type="hidden" name="id" value="<%= id %>">
                <input type="hidden" name="teste" value="<%= teste %>">
                <button type="submit">enviar E-mail novamente</button>
        </form>


        <div class="resultado">
            <%


                if ("POST".equalsIgnoreCase(request.getMethod()) && "apagar".equals(request.getParameter("acao"))) {
                    String codigoR = request.getParameter("codigoR");

                    if (codigo.equals(codigoR)) {
                        if(teste.equals("painelUsuario"))
                        {
                            response.setContentType("text/html;charset=UTF-8");
                            out.println("<form id='autoSubmit' action='AtualizarUsuario' method='POST'>");
                            out.println("<input type='hidden' name='email' value='" + email + "'>");
                            out.println("<input type='hidden' name='nome' value='" + nome + "'>");
                            out.println("<input type='hidden' name='cpf' value='" + cpf + "'>");
                            out.println("<input type='hidden' name='id' value='" + id + "'>");
                            out.println("</form>");
                            out.println("<script>document.getElementById('autoSubmit').submit();</script>");

                        } else{
                            response.setContentType("text/html;charset=UTF-8");
                            out.println("<form id='autoSubmit' action='CadastroServlet' method='POST'>");
                            out.println("<input type='hidden' name='email' value='" + email + "'>");
                            out.println("<input type='hidden' name='nome' value='" + nome + "'>");
                            out.println("<input type='hidden' name='cpf' value='" + cpf + "'>");
                            out.println("<input type='hidden' name='senha' value='" + senha + "'>");
                            out.println("</form>");
                            out.println("<script>document.getElementById('autoSubmit').submit();</script>");
                        }
                        
                    } else {
                        out.println("<p style='color:red;'>Código inválido!</p>");
                    }
                }
            %>
        </div>
    </main>
</div>
</body>
</html>
