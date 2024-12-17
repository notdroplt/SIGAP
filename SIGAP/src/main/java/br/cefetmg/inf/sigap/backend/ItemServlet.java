package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.stream.Collectors;

@WebServlet(name = "ItemServlet", urlPatterns = {"/api/item"})
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());

        System.out.println(json);

        //JSONObject jobj = HTTP.toJSONObject(jb.toString());
    }

}


