package br.cefetmg.inf.sigap.backend;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet(urlPatterns = "/AtualizarUsuario")
public class AtualizarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("id"));
        String cpf = UsuarioService.extrairCpf(request.getParameter("cpf"));
        if(!UsuarioService.validarCpf(cpf)){
            UsuarioService.printPage(out, "<h1>CPF inválido</h1><br><a href='painelUsuario.jsp'>Voltar</a>");
            return;
        }

        Usuario usuario = new Usuario(nome, email, null, cpf, id);
        if (UsuarioService.atualizarUsuario(usuario)) {
            response.sendRedirect("painelUsuario.jsp?usuarioAtualizado=true");
        } else {
            response.sendRedirect("painelUsuario.jsp?usuarioAtualizado=false");
        }
    }
}