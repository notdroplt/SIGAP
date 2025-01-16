package br.cefetmg.inf.sigap.backend;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        StringBuilder cpfString = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(request.getParameter("cpf"));
        while (matcher.find()) {
            cpfString.append(matcher.group());
        }

        long cpf = Long.parseLong(cpfString.toString());

        Usuario usuario = new Usuario(nome, email, null, cpf);
        if (UsuarioService.atualizarUsuario(id, usuario)) {
            response.sendRedirect("painelUsuario.jsp?usuarioAtualizado=true");
        } else {
            response.sendRedirect("painelUsuario.jsp?usuarioAtualizado=false");
        }
    }
}