package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import br.cefetmg.inf.sigap.db.UsuarioService;
import br.cefetmg.inf.sigap.db.Usuario;

@WebServlet(name = "UsuarioServle", urlPatterns = "/api/UsuarioServle")
public class UsuarioCadastroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        long cpf = Long.parseLong(request.getParameter("cpf"));

        Usuario usuario = new Usuario(nome, email, senha, cpf);
        String token = UsuarioService.criarUsuario(usuario);

        HttpSession session = request.getSession(true);
        session.setAttribute("Token", token);
    }
}