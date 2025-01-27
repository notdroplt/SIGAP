package br.cefetmg.inf.sigap.service;

/*
 *
 * @author luisg
 */

import br.cefetmg.inf.sigap.dto.Item;
import br.cefetmg.inf.sigap.dto.StatusItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    private final String dbUrl = "jdbc:postgresql://db:5432/sigap";
    private final String dbUser = "sigap";
    private final String dbPassword = "sigap";

    public List<Item> getItensPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        List<Item> itens = new ArrayList<>();

        String sql = "SELECT * FROM Item WHERE (dataPerdido BETWEEN ? AND ?) OR (dataAchado BETWEEN ? AND ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(dataInicio));
            stmt.setDate(2, Date.valueOf(dataFim));
            stmt.setDate(3, Date.valueOf(dataInicio));
            stmt.setDate(4, Date.valueOf(dataFim));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getLong("uid"),
                            rs.getString("nome"),
                            rs.getInt("cor"),
                            rs.getString("marca"),
                            rs.getDate("dataPerdido") != null ? rs.getDate("dataPerdido").toLocalDate() : null,
                            rs.getDate("dataAchado") != null ? rs.getDate("dataAchado").toLocalDate() : null,
                            null,
                            rs.getString("local"),
                            rs.getString("descricao"),
                            rs.getString("lugarAchado"),
                            rs.getString("lugarPerdido"),
                            rs.getString("foto"),
                            StatusItem.valueOf(rs.getString("status"))
                    );
                    itens.add(item);
                }
            }
        }
        return itens;
    }
}
