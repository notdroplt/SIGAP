package br.cefetmg.inf.sigap.service;

/**
 *
 * @author luisg
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public final class GraficoService {

    private static GraficoService single = null;

    private static final String jdbcURL = "jdbc:postgresql://db:5432/sigap";
    private static final String username = "sigap";
    private static final String password = "sigap";

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(jdbcURL, username, password);
    }

    private GraficoService() {}

    public static GraficoService getInstance() {
        if (single == null) single = new GraficoService();

        return single;
    }

    public Map<String, Integer> getItensPerdidosPorLocal() {
        Map<String, Integer> resultados = new HashMap<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT lugar_perdido AS local, COUNT(*) AS quantidade " +
                         "FROM Item WHERE status = 0 " +
                         "GROUP BY lugar_perdido " +
                         "ORDER BY quantidade DESC";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.put(rs.getString("local"), rs.getInt("quantidade"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }

    public Map<String, Integer> getItensAchadosPorLocal() {
        Map<String, Integer> resultados = new HashMap<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT lugar_achado AS local, COUNT(*) AS quantidade " +
                         "FROM Item WHERE status = 1 " +
                         "GROUP BY lugar_achado " +
                         "ORDER BY quantidade DESC";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                resultados.put(rs.getString("local"), rs.getInt("quantidade"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }
}
