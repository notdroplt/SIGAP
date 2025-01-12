package br.cefetmg.inf.sigap.controller;

/**
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.db.RelatorioService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {

    private final RelatorioService relatorioService = RelatorioService.getInstance();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, Integer> dados = relatorioService.getQuantidadeItensPorLocal();

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
