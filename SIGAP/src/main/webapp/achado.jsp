<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAA</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="perdido.css">
</head>

<body>
    <div class="container">
        <header>
            <div class="title">
                <a style="color: white; text-decoration: inherit;" href="home.jsp">
                    <h1>CEFET-MG - SIGAP - Cadastrar Item Achado</h1>
                </a>
                <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
                <button id="homeButton" onclick="window.location.href='home.jsp'">Home</button>
            </div>

        </header>

        <main>

            <div id="item-box">
                <p>Cadastro de Item Encontrado</p>
                <%@ include file="item.jsp" %>
                <div>
                    <label for="cor">Cor:</label>
                    <input type="color" id="cor-item" name="cor" placeholder="#696969">
                </div>
                <div>
                    <label for="marca">Marca:</label>
                    <input type="text" id="marca-item" name="marca" placeholder="Marca...">
                </div>
                <%@ include file="lugar.jsp" %>
                <%@ include file="descricao.jsp" %>
                <div>    
                    <label for="campus">Campus encontrado:</label>
                    <select name="campus" id="campus-item">
                        <option value="c1">Campus 1</option>
                        <option value="c2">Campus 2</option>
                    </select>
                </div>
                <div>
                    <label for="imagem">Imagem:</label>
                    <input type="file" name="imagem" id="imagem-item">
                </div>

                <div>
                    <label for="possui-nome">Possui nome e sobrenome?</label>
                    <input type="checkbox" id="possui-nome" name="possui-nome" onclick="toggleNomeSobrenome()">
                </div>
                <div id="nome-sobrenome-container" style="display: none;">
                    <div>
                        <label for="nome">Nome:</label>
                        <input type="text" id="nome" name="nome" placeholder="Nome">
                    </div>
                    <div>
                        <label for="sobrenome">Sobrenome:</label>
                        <input type="text" id="sobrenome" name="sobrenome" placeholder="Sobrenome">
                    </div>
                </div>


                <button id="formSubmit" type="submit" onClick="submit('/SIGAP/api/cadastro/item/achado', event)">Carregar
                    Item</button>
                <div id="notification-container" style="position: fixed; top: 20px; right: 20px; z-index: 1000;"></div>
            </div>
        </main>
    </div>
    <script src="form.js"></script>
</body>

</html>