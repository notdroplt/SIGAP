package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.db.ItemService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate dataInicio = LocalDate.parse(req.getParameter("dataInicio"));
        LocalDate dataFim = LocalDate.parse(req.getParameter("dataFim"));

        ItemService service = ItemService.getInstance();

        if (dataInicio != null) {
            req.setAttribute("itens", service.getItemPorPeriodo(dataInicio, dataFim));
        } else {
            req.setAttribute("itens", service.getItens());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/relatorios/relatorioItens.jsp");
        dispatcher.forward(req, resp);
    }
}