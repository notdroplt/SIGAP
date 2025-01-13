package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import br.cefetmg.inf.sigap.db.ItemService;
import br.cefetmg.inf.sigap.db.Item.java;

@WebServlet(name = "PesquisaNome", urlPatterns = {"/PesquisaNome"})

public class PesquisaNome extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String nomeItem = request.getParameter("nomeItem");
                List<Item> alvos = ItemService.getItemPorNome(nomeItem);

                if (alvos != null && !alvos.isEmpty()){
                    for (Item item : alvos) {
                        out.print("<div class=\"acoes\"><span>" + item.getNome() + "</span><input type=\"button\""
                                  + " value=\"Adicionar\" onclick=\"adicionaritem('" + item.getNome() + "')\"></div>");
                    }
                }
                else {
                    out.print("<center>Nenhum Item com este nome encontrado.</center>"
                            + "<center><button type=\"button\" style=\"margin-top: 1em;\" "
                            + "onclick=\"window.location.reload();\">Tentar Novamente</button></center>");
                }
            }
        }
}