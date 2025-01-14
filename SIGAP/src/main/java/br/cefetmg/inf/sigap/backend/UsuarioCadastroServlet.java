package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.cefetmg.inf.sigap.service.UsuarioService;
import br.cefetmg.inf.sigap.dto.Usuario;

@WebServlet(urlPatterns = "/CadastroServlet")
public class UsuarioCadastroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        StringBuilder cpfString = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(request.getParameter("cpf"));
        while (matcher.find()) {
            cpfString.append(matcher.group());
        }

        long cpf = Long.parseLong(cpfString.toString());

        Usuario usuario = new Usuario(nome, email, senha, cpf);
        int id;
        if (UsuarioService.criarUsuario(usuario)) {
            id = UsuarioService.getToken(cpf, senha);
            HttpSession session = request.getSession(true);
            session.setAttribute("Token", id);

            out.println("<html><body>");
            out.println("<h1>Usuário criado com sucesso!</h1>");
            out.println("<p>Nome: " + nome + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Token: " + id + "</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Erro ao criar usuário!</h1>");
            out.println("</body></html>");
        }
        out.close();
    }
}