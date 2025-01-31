package br.cefetmg.inf.sigap.controller;

/*
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.dto.Item;
import br.cefetmg.inf.sigap.service.RelatorioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {
    private final RelatorioService relatorioService = new RelatorioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String dataInicioStr = request.getParameter("dataInicio");
            String dataFimStr = request.getParameter("dataFim");

            LocalDate dataInicio = LocalDate.parse(dataInicioStr);
            LocalDate dataFim = LocalDate.parse(dataFimStr);

            List<Item> itens = relatorioService.getItensPorPeriodo(dataInicio, dataFim);
            request.setAttribute("itens", itens);

            request.getRequestDispatcher("/Relatorio.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao gerar relatório: " + e.getMessage());
            request.getRequestDispatcher("/Relatorio.jsp").forward(request, response);
        }
    }
}
