package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dao.UsuarioDao;
import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        long cpf = UsuarioService.extrairCpf(request.getParameter("cpf"));
        byte[] hash = UsuarioService.hashSenha(senha);
        Usuario oldUsuario = UsuarioService.getUserData(id);
        Usuario usuario = null;
        if(senha != null){
            usuario = new Usuario(nome, email, hash, cpf, id);
        }
        else{
            usuario = new Usuario(nome, email, oldUsuario.getSenha(), cpf, id);
        }

        if(UsuarioDao.verificarAutoridade(authId, oldUsuario.getAutoridade())) {
            usuario.setAutoridade(oldUsuario.getAutoridade());
        } else {
            UsuarioService.printPage(out, "<p>Você não tem permissão para alterar esse usuário</p><br><a href='listaUsuarios.jsp'>Voltar</a>");
            return;
        }
        if (UsuarioService.atualizarUsuario(authId, usuario)) {
            UsuarioService.printPage(out, "<p>Usuario atualizado com sucesso</p><br><a href='listaUsuarios.jsp'>Voltar</a>");
        } else {
            UsuarioService.printPage(out,"<p>Erro ao atualizar usuario</p><br><a href='listaUsuarios.jsp'>Voltar</a>");
        }
    }
}