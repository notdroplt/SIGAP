package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.service.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/cadastro/item/perdido"})
public class CadastroItemPerdidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("get em /api/cadastro/item");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Requisição GET Inválida</h1>");
    }

    protected void ErrorResponse(HttpServletResponse res, String motivo) throws IOException {
        JSONObject respjson = new JSONObject();
        respjson.put("sucesso", false);
        respjson.put("motivo", motivo);
        res.getOutputStream().println(respjson.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Post em /api/cadastro/item/perdido ");
        String json = req.getReader().lines().collect(Collectors.joining());
        JSONObject jsonbj = new JSONObject(json);

        String nome = jsonbj.getString("nome");

        if (nome.length() > 40) {
            System.out.println("Erro: Tamanho do nome " + nome.length() + " > 40");
            ErrorResponse(res, "texto-nome");
            return;
        }

        String desc = jsonbj.getString("desc");

        if (desc.length() > 144) {
            System.out.println("Erro: Tamanho da descrição " + nome.length() + " > 144");
            ErrorResponse(res, "texto-desc");
            return;
        }

        String local = jsonbj.getString("lugar");

        if (local.length() > 144) {
            System.out.println("Erro: Tamanho do local " + local.length() + " > 144");
            ErrorResponse(res, "texto-local");
            return;
        }

        String campus = jsonbj.getString("campus");


        if (campus.length() > 3) {
            System.out.println("Erro: Tamanho do campus " + campus.length() + " > 3");

            ErrorResponse(res, "texto-campus");
            return;
        }

        String marca = jsonbj.getString("marca");

        if (marca.length() > 60) {
            System.out.println("Erro: Tamanho da marca " + marca.length() + " > 60");
            ErrorResponse(res, "texto-marca");
            return;
        }

        String cor = jsonbj.getString("cor");

        if (cor.length() != 7) {
            System.out.println("Erro: Cor " + cor.length() + " != 7");
            ErrorResponse(res, "texto-cor");
            return;
        }

        String imagem = jsonbj.optString("imagem");

        if (imagem.isEmpty()) {
            System.out.println("Erro ao adicionar imagem");
            ErrorResponse(res, "imagem");
            return;
        }

        Integer valorCor = ItemService.reduzirEspectroCor(cor);

        ImagemService img_service = ImagemService.getInstance();

        String caminho = img_service.adicionarImagem(imagem);

        System.out.println("imagem salva em " + caminho);

        ItemService service = ItemService.getInstance();

        service.adicionarItemPerdido(
                0L, nome, valorCor, marca, LocalDate.now(), desc, local, campus, caminho
        );

        System.out.println("Item adicionado!");


        JSONObject respjson = new JSONObject();

        respjson.put("sucesso", true);

        res.getOutputStream().println(respjson.toString());
    }

}


