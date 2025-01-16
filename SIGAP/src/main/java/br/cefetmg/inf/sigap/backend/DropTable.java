package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.service.GeneralService;
import br.cefetmg.inf.sigap.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/dropTable")
public class DropTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method='get' action='dropTable'>");
        out.println("Nome da Tabela: <input type='text' name='db' />");
        out.println("<input type='submit' value='Deletar Tabela' />");
        out.println("</form>");
        out.println("</body></html>");
        String table = request.getParameter("db");
        if(table == null){
            out.println("Erro: Tabela não informada");
            out.close();
            return;
        }
        String sql = "DROP TABLE " + table;
        try {
            GeneralService.dropTable(sql);
            out.println("Tabela " + table + " deletada com sucesso");
        } catch (Exception e) {
            out.println("Erro ao deletar tabela " + table);
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
