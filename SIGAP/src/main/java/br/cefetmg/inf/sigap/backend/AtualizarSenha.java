package br.cefetmg.inf.sigap.backend;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet(urlPatterns = "/AtualizarSenha")
public class AtualizarSenha extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String senha = request.getParameter("senhaOld");
        String novaSenha = request.getParameter("senha");
        int id = Integer.parseInt(request.getParameter("id"));
        byte[] hash = UsuarioService.hashSenha(senha);
        byte[] hashNovaSenha = UsuarioService.hashSenha(novaSenha);

        if (UsuarioService.atualizarSenha(id, hashNovaSenha, hash)) {
            response.sendRedirect("painelUsuario.jsp?senhaAtualizada=true");
        } else {
            response.sendRedirect("painelUsuario.jsp?senhaAtualizada=false");
        }
    }
}