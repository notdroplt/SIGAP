package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import br.cefetmg.inf.sigap.services.ItemService;
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
                List<Item> alvos = servicoItem.getItens();
                List<Item> itensFiltrados = new ArrayList<>();

                switch(filtro){
                    case "nome":
                        for (Item item : alvos) {
                            if (item.getNome().equalsIgnoreCase(valor))
                                itensFiltrados.add(item);
                        }
                        break;
                    case "cor":
                        try{
                            int cor = Integer.parseInt(valor);
                            for (Item item : alvos) {
                                if (item.getCor() == cor)
                                    itensFiltrados.add(item);
                            }
                        }catch (NumberFormatException e){
                            itensFiltrados = List.of();
                        }
                        break;
                    case "marca":
                        for (Item item : alvos) {
                            if (item.getMarca().equalsIgnoreCase(valor))
                                itensFiltrados.add(item);
                        }
                        break;
                    default:
                        itensFiltrados = List.of();  // Caso o filtro não seja válido
                        break;
                }

                request.setAttribute("itensEncontrados", itensFiltrados);
                RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoPesquisa.jsp");
                dispatcher.forward(request, response);
            }
        }
}