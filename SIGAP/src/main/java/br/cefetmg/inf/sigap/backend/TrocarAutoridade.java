package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet(urlPatterns = "/TrocarAutoridade")
public class TrocarAutoridade extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("id"));
        int auth = Integer.parseInt(request.getParameter("auth"));
        int authId = Integer.parseInt(request.getParameter("authId"));

        if(UsuarioService.trocarAutoridade(id, auth, authId)){
            response.getWriter().println("Autoridade trocada com sucesso!");
            response.getWriter().println("<a href='index.jsp'>Voltar</a>");
        }
        else {
            response.getWriter().println("Erro ao trocar autoridade!");
            response.getWriter().println("<a href='listaUsuarios.jsp'>Voltar</a>");
        }
    }
}