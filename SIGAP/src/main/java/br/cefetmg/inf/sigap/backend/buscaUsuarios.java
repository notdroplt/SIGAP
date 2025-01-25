package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/buscaUsuarios")
public class buscaUsuarios extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String category = request.getParameter("category");

        // Assuming UsuarioService has a method to search users based on category and query
        Usuario[] usuarios = UsuarioService.listarUsuarios(query, category);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(usuarios));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}