<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.time.LocalDate, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>CEFET-MG - SIGAP</title>
    </head>
    <body>
        <header>
            <div class="title">
                <h1>CEFET-MG - SIGAP</h1>
                <span>Sistema Integrado de Gestão de Achados e Perdidos</span>
            </div>
        </header>
        <div class="container">
            <h2>Relatório de Itens</h2>
            <form method="post">
                <label for="dataInicio">Data de Início:</label>
                <input type="date" id="dataInicio" name="dataInicio" required>
                <label for="dataFim">Data de Fim:</label>
                <input type="date" id="dataFim" name="dataFim" required>
                <button type="submit">Gerar Relatório</button>
            </form>
            <form action="Escolha.jsp" method="get">
                <button type="submit" class="btn">Voltar</button>
            </form>
            <div class="relatorio">
                <%
                    String dataInicioStr = request.getParameter("dataInicio");
                    String dataFimStr = request.getParameter("dataFim");
                    List<Map<String, String>> itens = new ArrayList<>();

                    if (dataInicioStr != null && dataFimStr != null) {
                        LocalDate dataInicio = LocalDate.parse(dataInicioStr);
                        LocalDate dataFim = LocalDate.parse(dataFimStr);
                        String dbUrl = "jdbc:postgresql://db:5432/sigap"; 
                        String dbUser = "sigap";
                        String dbPassword = "sigap";
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;

                        try {
                            Class.forName("org.postgresql.Driver");
                            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                            String sql = "SELECT * FROM Item WHERE (data_perdido BETWEEN ? AND ?) OR (data_achado BETWEEN ? AND ?)";
                            stmt = conn.prepareStatement(sql);
                            stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
                            stmt.setDate(2, java.sql.Date.valueOf(dataFim));
                            stmt.setDate(3, java.sql.Date.valueOf(dataInicio));
                            stmt.setDate(4, java.sql.Date.valueOf(dataFim));
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                Map<String, String> item = new HashMap<>();
                                item.put("id", rs.getString("uid"));
                                item.put("nome", rs.getString("nome"));
                                item.put("dataPerdido", rs.getDate("data_perdido") != null ? rs.getDate("data_perdido").toString() : "N/A");
                                item.put("dataAchado", rs.getDate("data_achado") != null ? rs.getDate("data_achado").toString() : "N/A");
                                item.put("local", rs.getString("local"));
                                item.put("status", rs.getString("status"));
                                itens.add(item);
                            }
                        } catch (Exception e) {
                            out.println("<p style='color:red;'>Erro ao gerar relatório: " + e.getMessage() + "</p>");
                        } finally {
                            try { if(rs != null) rs.close(); } catch(SQLException e) {}
                            try { if(stmt != null) stmt.close(); } catch(SQLException e) {}
                            try { if(conn != null) conn.close(); } catch(SQLException e) {}
                        }
                    }
                    request.setAttribute("itens", itens);
                %>

                <c:if test="${not empty itens}">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Data Perdido</th>
                                <th>Data Achado</th>
                                <th>Local</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${itens}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.nome}</td>
                                    <td>${item.dataPerdido}</td>
                                    <td>${item.dataAchado}</td>
                                    <td>${item.local}</td>
                                    <td>${item.status}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty itens}">
                    <p>Nenhum item encontrado no período selecionado.</p>
                </c:if>
            </div>
        </div>
    </body>
</html>
