package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.db.ItemService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import org.json.JSONObject;

import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/cadastro/item"})
public class CadastroItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("get em /api/cadastro/item");
        String nome = request.getParameter("nome");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Requisição GET Inválida</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Post em /api/cadastro/item ");
        String json = req.getReader().lines().collect(Collectors.joining());
        JSONObject jsonbj = new JSONObject(json);
        for (String key : jsonbj.keySet()) {
            System.out.println("  " + key + ": " + jsonbj.getString(key));
        }
        String nome = jsonbj.getString("nome-item");
        String desc = jsonbj.getString("desc-item");
        String local = jsonbj.getString("lugar-item");

        ItemService.adicionarItemPerdido(
            nome, new Date(), desc, local, "/dev/zero"
        );
        System.out.println("Item adicionado!");

        //JSONObject jobj = HTTP.toJSONObject(jb.toString());
    }

}


