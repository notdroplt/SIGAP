package br.cefetmg.inf.sigap.controller;

/**
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.db.RelatorioService;
import br.cefetmg.inf.sigap.db.Item;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {

    private final RelatorioService relatorioService = RelatorioService.getInstance();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String tipo = request.getParameter("tipo");
            Map<String, Integer> dados = null;
            List<Item> itens = null;

            switch (tipo) {
                case "local":
                    dados = relatorioService.getQuantidadeItensPorLocal();
                    request.setAttribute("tipoRelatorio", "Itens por Local");
                    break;
                case "status":
                    dados = relatorioService.getQuantidadeItensPorStatus();
                    request.setAttribute("tipoRelatorio", "Itens por Status");
                    break;
                case "periodo":
                    String inicio = request.getParameter("inicio");
                    String fim = request.getParameter("fim");
                    if (inicio != null && fim != null) {
                        dados = relatorioService.getQuantidadeItensPorPeriodo(inicio, fim);
                        request.setAttribute("tipoRelatorio", "Itens por Período");
                        request.setAttribute("periodo", String.format("De %s até %s", inicio, fim));
                    }
                    break;
                case "nome":
                    String nome = request.getParameter("nome");
                    if (nome != null) {
                        itens = relatorioService.getItensPorNome(nome);
                        request.setAttribute("tipoRelatorio", "Itens por Nome");
                        request.setAttribute("nomePesquisa", nome);
                    }
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Tipo de relatório inválido.");
                    return;
            }

            if (dados != null) {
                request.setAttribute("dados", dados);
            }

            if (itens != null) {
                request.setAttribute("itens", itens);
            }

            request.getRequestDispatcher("/relatorio.jsp").forward(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao processar a requisição: " + e.getMessage());
        }
    }
}

