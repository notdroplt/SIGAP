package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dao.UsuarioDao;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet(urlPatterns = "/EditarUsuario")
public class EditarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        int id = Integer.parseInt(request.getParameter("id"));
        int authId = Integer.parseInt(request.getParameter("authId"));
        String cpf = UsuarioService.extrairCpf(request.getParameter("cpf"));
        byte[] hash = UsuarioService.hashSenha(senha);
        Usuario oldUsuario = UsuarioService.getUserData(id);
        Usuario usuario;
        if(senha != null){
            usuario = new Usuario(nome, email, hash, cpf, id);
        }
        else{
            usuario = new Usuario(nome, email, oldUsuario.getSenha(), cpf, id);
        }
        if(UsuarioService.validarCpf(cpf)){
            UsuarioService.printPage(out, "<p>CPF inválido</p><br><a href='listaUsuarios'>Voltar</a>");
            return;
        }

        if(UsuarioDao.verificarAutoridade(authId, oldUsuario.getAutoridade())) {
            usuario.setAutoridade(oldUsuario.getAutoridade());
        } else {
            UsuarioService.printPage(out, "<p>Você não tem permissão para alterar esse usuário</p><br><a href='listaUsuarios'>Voltar</a>");
            return;
        }
        if (UsuarioService.atualizarUsuario(authId, usuario)) {
            UsuarioService.printPage(out, "<p>Usuario atualizado com sucesso</p><br><a href='listaUsuarios'>Voltar</a>");
        } else {
            UsuarioService.printPage(out,"<p>Erro ao atualizar usuario</p><br><a href='listaUsuarios'>Voltar</a>");
        }
    }
}