package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import br.cefetmg.inf.sigap.db.*;

@WebServlet(name = "PesquisaMarca", urlPatterns = {"/PesquisaMarca"})

public class PesquisaMarca extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String MarcaItem = request.getParameter("MarcaItem");
            ItemService alvo = getItemPorMarca(MarcaItem);

            if (alvo != null){
                out.print("<div class=\"acoes\"><span>"+ alvo.getNome() +"</span><input type=\"button\""
                        + " value=\"Adicionar\" onclick=\"adicionaritem('"+ alvo.getNome() +"')\"></div>");
            }
            else {
                out.print("<center>Nenhum Item com esta marca encontrado.</center>"
                        + "<center><button type=\"button\" style=\"margin-top: 1em;\" "
                        + "onclick=\"window.location.reload();\">Tentar Novamente</button></center>");
        }
    }
}