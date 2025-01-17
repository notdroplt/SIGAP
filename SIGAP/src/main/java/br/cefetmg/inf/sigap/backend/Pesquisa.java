package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.List;
import br.cefetmg.inf.sigap.db.ItemService;
import br.cefetmg.inf.sigap.db.Item;

@WebServlet(name = "Pesquisa", urlPatterns = {"/Pesquisa"})

public class Pesquisa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String filtro = request.getParameter("filtro");
                String valor = request.getParameter("valor");

                ItemService servicoItem = ItemService.getInstance();
                List<Item> alvos = null;

                switch(filtro){
                    case "nome":
                        alvos = servicoItem.getItemPorNome(valor);
                        break;
                    default:
                        alvos = List.of();
                        break;
                }

                request.setAttribute("itensEncontrados", alvos);

                RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoPesquisa.jsp");
                dispatcher.forward(request, response);
            }
        }
}