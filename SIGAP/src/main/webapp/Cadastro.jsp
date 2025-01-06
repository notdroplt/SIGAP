<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CEFET-MG - SIGAA</title>
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

    <main>

        <div class="login-box">
            <h2>Cadastre-se no Sistema</h2>
            <form action="CadastroServlet" method="POST">
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf" placeholder="CPF">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" placeholder="Nome completo">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Email">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" placeholder="Senha">
                <button type="submit">Entrar</button>
            </form>
        </div>

        <div class="help-links">
            <p><a href="index.jsp">Login</a> | <a href="#">Esqueci minha senha</a></p>

        </div>
    </main>
</div>
</body>
</html>