package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import br.cefetmg.inf.sigap.db.*;

@WebServlet(name = "PesquisaCor", urlPatterns = {"/PesquisaCor"})

public class PesquisaCor extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String CorItem = request.getParameter("CorItem");
            ItemService alvo = getItemPorCor(CorItem);

            if (alvo != null){
                out.print("<div class=\"acoes\"><span>"+ alvo.getNome() +"</span><input type=\"button\""
                        + " value=\"Adicionar\" onclick=\"adicionaritem('"+ alvo.getNome() +"')\"></div>");
            }
            else {
                out.print("<center>Nenhum Item com esta cor encontrado.</center>"
                        + "<center><button type=\"button\" style=\"margin-top: 1em;\" "
                        + "onclick=\"window.location.reload();\">Tentar Novamente</button></center>");
        }
    }
}