<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>CEFET-MG - SIGAP</title>
    </head>
    <body>
        <div class="container">
            <header>
                <div class="title">
                    <h1>CEFET-MG - SIGAP -</h1>
                    <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
                </div>
            </header>
            <h2>Escolha a Opção Desejada</h2>
            <div class="buttons">
                <form action="Relatorio.jsp" method="get">
                    <button type="submit" class="btn">Relatório de Itens</button>
                </form>
                <form action="Grafico.jsp" method="get">
                    <button type="submit" class="btn">Gráfico de Itens</button>
                </form> 
                    <form action="home.jsp" method="get">
                        <button type="submit" class="btn">Voltar</button>
                    </form>
            </div>

        </div>
    </body>
</html>
