package br.cefetmg.inf.sigap.db;

/**
 *
 * @author luisg
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public final class RelatorioService {
    private static RelatorioService single = null;

    private static final String jdbcURL = "jdbc:postgresql://db:5432/sigap";
    private static final String username = "sigap";
    private static final String password = "sigap";

    private RelatorioService() {}

    public static RelatorioService getInstance() {
        if (single == null) {
            single = new RelatorioService();
        }
        return single;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public Map<String, Integer> getQuantidadeItensPorLocal() {
        Map<String, Integer> dados = new HashMap<>();

        String sql = "SELECT local, COUNT(*) AS quantidade FROM Item GROUP BY local ORDER BY local;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dados.put(rs.getString("local"), rs.getInt("quantidade"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter os dados para o relatório", e);
        }

        return dados;
    }
}

