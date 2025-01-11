package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import br.cefetmg.inf.sigap.db.*;

@WebServlet(name = "PesquisaNome", urlPatterns = {"/PesquisaNome"})

public class PesquisaNome extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String nomeItem = request.getParameter("nomeItem");
                ItemService alvo = getItemPorNome("nomeItem");

                if (alvo != null){
                    out.print("<div class=\"acoes\"><span>"+ alvo.getNome() +"</span><input type=\"button\""
                            + " value=\"Adicionar\" onclick=\"adicionaritem('"+ alvo.getNome() +"')\"></div>");
                }
                else {
                    out.print("<center>Nenhum Item com este nome encontrado.</center>"
                            + "<center><button type=\"button\" style=\"margin-top: 1em;\">"
                            + "</a></button></center>");
                }
            }
        }
}