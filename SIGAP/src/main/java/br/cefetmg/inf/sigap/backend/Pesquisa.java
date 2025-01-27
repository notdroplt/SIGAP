package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import br.cefetmg.inf.sigap.service.ItemService;
import br.cefetmg.inf.sigap.dto.Item;

@WebServlet(name = "Pesquisa", urlPatterns = {"/Pesquisa"})

public class Pesquisa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String valor = request.getParameter("valor");
                String[] filtrosSelecionados = request.getParameterValues("filtros");

                if (valor != null && !valor.isEmpty()){
                    Cookie pesquisaCookie = new Cookie("pesquisa_" + valor, valor);
                    pesquisaCookie.setMaxAge(30 * 24 * 60 * 60);
                    response.addCookie(pesquisaCookie);
                }

                ItemService servicoItem = ItemService.getInstance();
                List<Item> alvos = servicoItem.getItens();
                List<Item> itensFiltrados = new ArrayList<>();

                for (Item item : alvos) {
                    boolean matches = true;

                    if (filtrosSelecionados != null) {
                        for (String filtro : filtrosSelecionados) {
                            switch (filtro) {
                                case "nome":
                                    matches = matches && item.getNome().toLowerCase().contains(valor.toLowerCase());
                                    break;
                                case "cor":
                                    try {
                                        int cor = Integer.parseInt(valor);
                                        matches = matches && item.getCor() == cor;
                                    } catch (NumberFormatException e) {
                                        matches = false;
                                    }
                                    break;
                                case "marca":
                                    matches = matches && item.getMarca().toLowerCase().contains(valor.toLowerCase());
                                    break;
                                default:
                                    matches = false;
                                    break;
                            }
                        }
                    }
                    if (matches) {
                        itensFiltrados.add(item);
                    }
                }


                request.setAttribute("itensEncontrados", itensFiltrados);
                RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoPesquisa.jsp");
                dispatcher.forward(request, response);
            }
        }
}