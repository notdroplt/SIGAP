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

        StringBuilder cpfString = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(request.getParameter("cpf"));
        while (matcher.find()) {
            cpfString.append(matcher.group());
        }

        long cpf = Long.parseLong(cpfString.toString());
        int id;
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        if (UsuarioService.login(cpf, senha)) {
            id = UsuarioService.getId(cpf, senha);
            HttpSession session = request.getSession(true);
            session.setAttribute("Token", id);
            response.getWriter().println("<h1>Login successful!</h1>");
            response.getWriter().println("<p>Token: " + id + "</p>");
            response.sendRedirect("painelUsuario.jsp");
        } else {
            response.getWriter().println("<h1>Login failed!</h1>");
        }
        response.getWriter().println("</body></html>");

    }
}