package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.db.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UsuarioServle", value = "/UsuarioServle")
public class UsuarioLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String senha = request.getParameter("senha");
        long cpf = Long.parseLong(request.getParameter("cpf"));

        String token = UsuarioService.login(cpf, senha);

        HttpSession session = request.getSession(true);
        session.setAttribute("Token", token);
    }
}