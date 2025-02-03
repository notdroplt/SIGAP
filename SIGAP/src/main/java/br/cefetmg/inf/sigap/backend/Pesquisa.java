package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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
                String itemSelecionado = request.getParameter("item");
                String marcaSelecionada = request.getParameter("valor-marca");
                String descSelecionada = request.getParameter("desc");
                String campusSelecionado = request.getParameter("campus");

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

                    if (itemSelecionado != null && !itemSelecionado.isEmpty()){
                        matches = item.getNome().equalsIgnoreCase(itemSelecionado);
                    }

                    if (marcaSelecionada != null && !marcaSelecionada.isEmpty()){
                        matches = matches && item.getMarca().toLowerCase().contains(Objects.requireNonNull(valor).toLowerCase());
                    }

                    if (descSelecionada != null && !descSelecionada.isEmpty()){
                        matches = item.getDescricao().equalsIgnoreCase(descSelecionada);
                    }

                    if (campusSelecionado != null && !campusSelecionado.isEmpty()){
                        matches = item.getLocal().equalsIgnoreCase(campusSelecionado);
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