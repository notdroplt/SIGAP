package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dao.GeneralDao;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("Token");
        GeneralDao.logAction(id, "Logout from user "+id);
        session.setAttribute("Token", 0);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
}