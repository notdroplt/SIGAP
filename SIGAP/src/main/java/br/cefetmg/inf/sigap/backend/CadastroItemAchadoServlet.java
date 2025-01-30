package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dto.Item;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.ImagemService;
import br.cefetmg.inf.sigap.service.ItemService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
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
        respjson.put("sucesso", false);
        respjson.put("motivo", razao);
        res.getOutputStream().println(respjson.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Post em /api/cadastro/item/achado ");
        String json = req.getReader().lines().collect(Collectors.joining());
        JSONObject jsonbj = new JSONObject(json);

        String nome = jsonbj.optString("nome");

        if (nome.isEmpty() || nome.length() > 40) {
            System.out.println("Erro: Tamanho do nome " + nome.length());
            ErrorResponse(res, "texto-nome");
            return;
        }

        String desc = jsonbj.optString("desc");

        if (desc.isEmpty() || desc.length() > 144) {
            System.out.println("Erro: Tamanho da descrição " + nome.length());
            ErrorResponse(res, "texto-desc");
            return;
        }

        String local = jsonbj.optString("lugar");

        if (local.isEmpty() || local.length() > 144) {
            System.out.println("Erro: Tamanho do local " + local.length() );
            ErrorResponse(res, "texto-local");
            return;
        }

        String campus = jsonbj.optString("campus");

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

//        String nomePessoa = jsonbj.optString("nomePessoa");
//        String sobrenomePessoa = jsonbj.optString("sobrenomePessoa");
//
//        if(nomePessoa!=null && sobrenomePessoa!=null)
//        {
//            long []ids = BuscaAluno.buscaAluno(nomePessoa, sobrenomePessoa);
//            Item item = new Item((long) 1, nome, ItemService.reduzirEspectroCor(cor), marca, null, null, null, local, desc, null, null, imagem, null);
//            for(int i=0; i< ids.length; i++)
//                Notificar.notificar(item, ids[i]);
//        }

        Integer valorCor = ItemService.reduzirEspectroCor(cor);

        ImagemService img_service = ImagemService.getInstance();

        String caminho = img_service.adicionarImagem(imagem);

        System.out.println("imagem salva em " + caminho);

        ItemService service = ItemService.getInstance();

        HttpSession session = req.getSession(false);

        Object uid = session.getAttribute("Token");

        if (uid == null) {
            System.out.println("Erro: id de usuário == null");
            ErrorResponse(res, "uid");
            return;
        }

        long idX = service.adicionarItemAchado(
                (Long)uid, nome, valorCor, marca, LocalDate.now(), desc, local, campus, caminho
        );

        String nomePessoa = jsonbj.optString("nomePessoa");
        String sobrenomePessoa = jsonbj.optString("sobrenomePessoa");

        if(nomePessoa!=null && sobrenomePessoa!=null)
        {
            long []ids = BuscaAluno.buscaAluno(nomePessoa, sobrenomePessoa);
            Item item = service.getItemPorId(idX).get(0);
            for(int i=0; i< ids.length; i++)
                Notificar.notificar(item, ids[i]);
        }

        System.out.println("Item adicionado!");

        JSONObject respjson = new JSONObject();

        respjson.put("sucesso", true);

        res.getOutputStream().println(respjson.toString());
    }

}


