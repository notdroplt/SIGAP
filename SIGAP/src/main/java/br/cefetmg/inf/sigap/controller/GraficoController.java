package br.cefetmg.inf.sigap.controller;

/**
 *
 * @author luisg
 */
import br.cefetmg.inf.sigap.db.GraficoService;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/grafico")
public class GraficoController extends HttpServlet {

    private final GraficoService graficoService = GraficoService.getInstance();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        if (tipo == null || (!tipo.equals("perdidos") && !tipo.equals("achados"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Parâmetro 'tipo' inválido ou ausente. Use 'perdidos' ou 'achados'.");
            return;
        }

        try {
            Map<String, Integer> dados;

            if ("perdidos".equals(tipo)) {
                dados = graficoService.getItensPerdidosPorLocal();
            } else {
                dados = graficoService.getItensAchadosPorLocal();
            }

            String json = gson.toJson(dados);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a requisição: " + e.getMessage());
        }
    }
}
