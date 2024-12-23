/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package br.cefetmg.inf.sigap.backend;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luisg
 */

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/sigap";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "senha";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = """
                SELECT 
                    tipo, COUNT(*) AS total, campus 
                FROM itens 
                WHERE data BETWEEN ? AND ? 
                GROUP BY tipo, campus;
            """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(dataInicio));
            statement.setDate(2, Date.valueOf(dataFim));

            ResultSet resultSet = statement.executeQuery();

            Map<String, Object> reportData = new HashMap<>();
            while (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String campus = resultSet.getString("campus");
                int total = resultSet.getInt("total");

                reportData.put(tipo + "_" + campus, total);
            }

            JSONObject jsonResponse = new JSONObject(reportData);
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
            out.flush();
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao gerar o relatório.");
        }
    }
}

