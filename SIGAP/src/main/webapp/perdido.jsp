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
                <h1>CEFET-MG - SIGAP -</h1>
                <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
            </div>

        </header>

        <main>

            <div id="item-box">
                <p>Cadastro de Item Perdido</p>
                <div>
                    <label for="item">Nome do Item:</label>
                    <input type="text" id="nome-item" name="item" placeholder="Nome...">
                </div>
                <div>
                    <label for="cor">Cor:</label>
                    <input type="color" id="cor-item" name="cor" placeholder="#696969">
                </div>
                <!-- TODO: Colocar descrição como dropdown -->
                <div>
                    <label for="lugar">Lugar que foi perdido:</label>
                    <input type="text" id="lugar-item" name="lugar" placeholder="lugar em que foi perdido">
                </div>

                <div>
                    <label for="desc">Descrição:</label>
                    <input type="text" id="desc-item" name="desc" placeholder="Como esse item se parecia">
                </div>
                <div>    
                    <label for="campus">Campus de perda:</label>
                    <select name="campus" id="campus-item">
                        <option value="c1">Campus 1</option>
                        <option value="c2">Campus 2</option>
                    </select>
                </div>
                <button id="formSubmit" type="submit" onClick="submit('/SIGAP/api/cadastro/item', event)">Carregar
                    Item</button>
            </div>
        </main>
    </div>
    <script src="form.js"></script>
</body>

</html>