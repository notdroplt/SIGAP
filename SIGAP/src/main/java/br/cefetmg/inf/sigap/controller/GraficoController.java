package br.cefetmg.inf.sigap.controller;

/*
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.service.GraficoService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@WebServlet("/grafico")
public class GraficoController extends HttpServlet {
    private final GraficoService graficoService = new GraficoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String dataInicioStr = request.getParameter("dataInicio");
            String dataFimStr = request.getParameter("dataFim");

            LocalDate dataInicio = LocalDate.parse(dataInicioStr);
            LocalDate dataFim = LocalDate.parse(dataFimStr);

            Map<String, Integer> dadosGrafico = graficoService.getDadosGrafico(dataInicio, dataFim);

            String json = new Gson().toJson(dadosGrafico);

            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao gerar dados do gráfico: " + e.getMessage());
        }
    }
}
