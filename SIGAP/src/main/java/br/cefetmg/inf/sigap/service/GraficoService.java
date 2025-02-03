package br.cefetmg.inf.sigap.service;

/*
 *
 * @author luisg
 */
/*
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GraficoService {

    private final String dbUrl = "jdbc:postgresql://db:5432/sigap";
    private final String dbUser = "sigap";
    private final String dbPassword = "sigap";

    public Map<String, Integer> getDadosGrafico(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        Map<String, Integer> dados = new HashMap<>();

        String sqlAchados = "SELECT COUNT(*) AS total FROM Item WHERE dataAchado BETWEEN ? AND ?";
        String sqlPerdidos = "SELECT COUNT(*) AS total FROM Item WHERE dataPerdido BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            try (PreparedStatement stmtAchados = conn.prepareStatement(sqlAchados)) {
                stmtAchados.setDate(1, Date.valueOf(dataInicio));
                stmtAchados.setDate(2, Date.valueOf(dataFim));

                try (ResultSet rs = stmtAchados.executeQuery()) {
                    if (rs.next()) {
                        dados.put("Achados", rs.getInt("total"));
                    }
                }
            }

            try (PreparedStatement stmtPerdidos = conn.prepareStatement(sqlPerdidos)) {
                stmtPerdidos.setDate(1, Date.valueOf(dataInicio));
                stmtPerdidos.setDate(2, Date.valueOf(dataFim));

                try (ResultSet rs = stmtPerdidos.executeQuery()) {
                    if (rs.next()) {
                        dados.put("Perdidos", rs.getInt("total"));
                    }
                }
            }
        }
        return dados;
    }
}
*/