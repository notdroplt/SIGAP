package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.service.ItemService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/cadastro/item/achado"})
public class CadastroItemAchadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("get em /api/cadastro/item/achado");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Requisição GET Inválida</h1>");
    }

    protected void ErrorResponse(HttpServletResponse res, String razao) throws IOException {
        JSONObject respjson = new JSONObject();
        respjson.append("sucesso", false);
        respjson.append("motivo", razao);
        res.getOutputStream().println(respjson.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Post em /api/cadastro/item/achado ");
        String json = req.getReader().lines().collect(Collectors.joining());
        JSONObject jsonbj = new JSONObject(json);

        String nome = jsonbj.getString("nome-item");

        if (nome.length() > 40) {
            System.out.println("Erro: Tamanho do nome " + nome.length() + " > 40");
            ErrorResponse(res, "texto-nome");
            return;
        }

        String desc = jsonbj.getString("desc-item");

        if (desc.length() > 144) {
            System.out.println("Erro: Tamanho da descrição " + nome.length() + " > 144");
            ErrorResponse(res, "texto-desc");
            return;
        }

        String local = jsonbj.getString("lugar-item");

        if (local.length() > 144) {
            System.out.println("Erro: Tamanho do local " + local.length() + " > 144");
            ErrorResponse(res, "texto-local");
            return;
        }

        String campus = jsonbj.getString("campus-item");


        if (campus.length() > 3) {
            System.out.println("Erro: Tamanho do campus " + campus.length() + " > 3");
            ErrorResponse(res, "texto-campus");
            return;
        }

        ItemService service = ItemService.getInstance();

        service.adicionarItemAchado(
            0L, nome, LocalDate.now(), desc, local, campus, "/dev/zero"
        );

        System.out.println("Item adicionado!");


        JSONObject respjson = new JSONObject();

        respjson.append("sucesso", true);

        res.getOutputStream().println(respjson.toString());
    }

}


