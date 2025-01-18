package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

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
        byte[] hash = UsuarioService.hashSenha(senha);
        long cpf = UsuarioService.extrairCpf(request.getParameter("cpf"));

        Usuario usuario = new Usuario(nome, email, hash, cpf);
        int id;
        if (UsuarioService.criarUsuario(usuario)) {
            id = UsuarioService.getId(cpf, hash);
            HttpSession session = request.getSession(true);
            session.setAttribute("Token", id);
            response.sendRedirect("painelUsuario.jsp");
        } else {
            UsuarioService.printPage(out, "<h1>Erro ao criar usuário!</h1>");
        }
        out.close();
    }
}