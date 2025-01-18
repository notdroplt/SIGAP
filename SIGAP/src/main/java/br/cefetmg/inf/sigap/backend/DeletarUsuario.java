package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dto.Usuario;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet(urlPatterns = "/DeletarUsuario")
public class DeletarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("id"));
        int authId = Integer.parseInt(request.getParameter("authId"));
        PrintWriter out = response.getWriter();
        Usuario user = UsuarioService.getUserData(id);
        Usuario userAuth = UsuarioService.getUserData(authId);
        if(userAuth.getAutoridade() == 0){
            out.println("Você não tem permissão para remover usuários!");
            out.println("<a href='listaUsuarios.jsp'>Voltar</a>");
            return;
        }
        if(userAuth.getAutoridade() <= user.getAutoridade()){
            out.println("Você não tem permissão para remover usuários com autoridade igual ou maior que a sua!");
            out.println("<a href='listaUsuarios.jsp'>Voltar</a>");
            return;
        }
        if(UsuarioService.removerUsuario(id, authId)){
            out.println("Usuário removido com sucesso!");
            out.println("<a href='listaUsuarios.jsp'>Voltar</a>");
        }
        else{
            out.println("Erro ao remover usuário!");
            out.println("<a href='listaUsuarios.jsp'>Voltar</a>");
        }
    }
}