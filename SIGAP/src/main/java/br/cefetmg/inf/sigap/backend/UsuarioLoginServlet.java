package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/LoginServlet")
public class UsuarioLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String senha = request.getParameter("senha");
        long cpf = UsuarioService.extrairCpf(request.getParameter("cpf"));
        int id;
        response.setContentType("text/html");
        if (UsuarioService.login(cpf, senha)) {
            id = UsuarioService.getId(cpf, senha);
            HttpSession session = request.getSession(true);
            session.setAttribute("Token", id);;
            response.sendRedirect("painelUsuario.jsp");
        } else {
            UsuarioService.printPage(response.getWriter(), "<h1>Login failed!</h1>");
        }

    }
}