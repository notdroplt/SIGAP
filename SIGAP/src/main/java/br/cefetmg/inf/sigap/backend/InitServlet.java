package br.cefetmg.inf.sigap.backend;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitServlet extends HttpServlet {

    public void initDB() throws ClassNotFoundException, SQLException {
        String item_tabela =
                "CREATE TABLE IF NOT EXISTS Item (\n" +
                "    id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
                "    uid BIGINT,\n" +
                "    nome VARCHAR(255) NOT NULL,\n" +
                "    cor INT NOT NULL,\n" +
                "    marca TEXT NOT NULL,\n" +
                "    data_perdido DATE,\n" +
                "    data_achado DATE,\n" +
                "    data_devolvido DATE,\n" +
                "    local TEXT NOT NULL,\n" +
                "    descricao TEXT NOT NULL,\n" +
                "    lugar_achado TEXT,\n" +
                "    lugar_perdido TEXT,\n" +
                "    foto TEXT NOT NULL,\n" +
                "    status VARCHAR(255) NOT NULL\n" +
                ");";
        String user_tabela =
                "CREATE TABLE IF NOT EXISTS usuario (\n" +
                "    id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
                "    nome VARCHAR(255) NOT NULL,\n" +
                "    cpf VARCHAR(11) NOT NULL,\n" +
                "    email VARCHAR(255) NOT NULL,\n" +
                "    senha BIGINT NOT NULL\n" +
                ");\n";

        String[] extensions = new String[] {
                "CREATE EXTENSION IF NOT EXISTS pg_trgm;",
                "CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;"
        };

        String[] indexes = new String[]{
                "CREATE INDEX ON Item USING gin (descricao gin_trgm_ops);",
                "CREATE INDEX ON Item USING gin (nome gin_trgm_ops);",
                "CREATE INDEX ON Item USING gin (lugar_achado gin_trgm_ops);",
                "CREATE INDEX ON Item USING gin (lugar_perdido gin_trgm_ops);",
        };

        String jdbcURL  = "jdbc:postgresql://db:5432/sigap";
        String username = "sigap";
        String password = "sigap";

        Class.forName("org.postgresql.Driver");

        // Establish the connection
        Connection conn = DriverManager.getConnection(jdbcURL, username, password);

        Statement stmt = conn.createStatement();

        // cria tabelas
        stmt.addBatch(item_tabela);
        stmt.addBatch(user_tabela);

        stmt.executeBatch();
        System.out.println(" -- Tabelas criadas");

        for (String ex : extensions) {
            stmt.addBatch(ex);
        }

        for (String idx : indexes) {
            stmt.addBatch(idx);
        }

        stmt.executeBatch();
        System.out.println(" -- configuração das tabelas finalizada");


    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            initDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
