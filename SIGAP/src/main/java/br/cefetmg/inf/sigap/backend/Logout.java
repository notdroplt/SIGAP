package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("Token", 0);
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}