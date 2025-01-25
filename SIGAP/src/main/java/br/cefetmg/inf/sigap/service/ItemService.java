/***
 * @file ItemService.java
 *
 * @brief Serviço responsável por manejar itens dentro do banco de dados
 *
 * @author Arthur C
 */

<<<<<<<< HEAD:SIGAP/src/main/java/br/cefetmg/inf/sigap/services/ItemService.java
package br.cefetmg.inf.sigap.services;

import br.cefetmg.inf.sigap.db.Item;
import br.cefetmg.inf.sigap.db.StatusItem;
========
package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dto.Item;
import br.cefetmg.inf.sigap.dto.StatusItem;
>>>>>>>> main:SIGAP/src/main/java/br/cefetmg/inf/sigap/service/ItemService.java

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;


public final class ItemService {
    /**
     * Factory para geração de entities para transações do banco de dados
     */

    private static ItemService single = null;

    private static final String jdbcURL  = "jdbc:postgresql://db:5432/sigap";
    private static final String username = "sigap";
    private static final String password = "sigap";

    /**
     * Gera uma nova conexão com o banco de dados
     *
     * @return Connection conexão gerada
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        // Establish the connection
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    private ItemService() { }

    /**
     * Transformar o serviço em singleton elimina problemas de paralelismo
     * @return instância de ItemService
     */
    public static ItemService getInstance() {
        if (single == null) single = new ItemService();

        return single;
    }

    /**
     * Adiciona um item perdido ao banco de dados
     * @param uid Id do usuário que cadastrou o item perdido
     * @param nome Nome do item perdido
     * @param data_perdido Data em que o item foi perdido
     * @param descricao Descrição do item perdido
     * @param lugar Lugar em que o item foi perdido
     * @param campus Campus de perda
     * @param foto Caminho para a foto do item
     */
    public synchronized void adicionarItemPerdido(Long uid, String nome, Integer cor, String marca, LocalDate data_perdido, String descricao, String lugar, String campus, String foto) {
        try {
            Connection conn = getConnection();

            System.out.println("===== ID DE USUÁRIO TESTE SENDO UTILIZADO =====");


            String sql = "INSERT INTO Item (" +
                    "uid, nome, cor, marca, data_perdido, descricao, lugar_perdido, local," +
                    "foto, data_achado, data_devolvido, lugar_achado, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, 0)";

            System.out.println("Criando item: \"" + nome
                           + "\", perdido em: " + data_perdido.format(DateTimeFormatter.ISO_LOCAL_DATE)
                           + ", descrição: \"" + descricao + "\", lugar: " + lugar);

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, uid);
            stmt.setString(2, nome);
            stmt.setInt(3, cor);
            stmt.setString(4, marca);
            stmt.setObject(5, data_perdido);
            stmt.setString(6, descricao);
            stmt.setString(7, lugar);
            stmt.setString(8, campus);
            stmt.setString(9, foto);

            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adiciona um item achado ao banco de dados
     * <p>
     * TODO: Fazer com que um item perdido seja mesclado com um item encontrado
     *
     * @param nome Nome do item achado
     * @param data_achado Data em que o item foi achado
     * @param descricao Descrição do item achado
     * @param lugar Lugar em que o item foi achado
     * @param campus Campus em que o item foi perdido
     * @param foto Foto do item
     */
    public synchronized void adicionarItemAchado(Long uid, String nome, Integer cor, String marca, LocalDate data_achado, String descricao, String lugar, String campus, String foto) {
        try {
            Connection conn = getConnection();

            System.out.println("===== ID DE USUÁRIO TESTE SENDO UTILIZADO =====");

            String sql = "INSERT INTO Item (" +
                    "uid, nome, cor, marca, data_achado, descricao, lugar_achado, local," +
                    "foto, data_perdido, data_devolvido, lugar_perdido, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, 1)";

            System.out.println("Criando item: \"" + nome
                    + "\", achado em: " + data_achado.format(DateTimeFormatter.ISO_LOCAL_DATE)
                    + ", descrição: \"" + descricao + "\", lugar: " + lugar);

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, uid);
            stmt.setString(2, nome);
            stmt.setInt(3, cor);
            stmt.setString(4, marca);
            stmt.setObject(5, data_achado);
            stmt.setString(6, descricao);
            stmt.setString(7, lugar);
            stmt.setString(8, campus);
            stmt.setString(9, foto);

            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void mesclarAchadoComPerdido(Item achado, Item perdido) {

        /// Mesclar itens

        /// Invocar função de e-mail

    }

    /**
     * Wrapper para adicionar um objeto de item ao invés de vários parâmetros diferentes
     * @param item item perdido para ser adicionado
     */
    public void adicionarItemPerdido(Long uid, Item item) {
        adicionarItemPerdido(uid, item.getNome(), item.getCor(), item.getMarca(), item.getDataPerdido(), item.getDescricao(), item.getLugarPerdido(), item.getLocal(), item.getFoto());
    }

    /**
     * Função para transformar um resultado de query numa lista de itens
     * @param rs ResultSet de uma query
     * @return Lista de itens
     */
    private List<Item> getItemPorRs(ResultSet rs) throws SQLException {
        List<Item> itens = new ArrayList<>();
        while(rs.next()) {
            Item it = new Item();
            it.setId(rs.getLong("id"));
            it.setUid(rs.getLong("uid"));
            it.setNome(rs.getString("nome"));
            it.setMarca(rs.getString("marca"));
            it.setCor(rs.getInt("cor"));
            it.setDataPerdido(rs.getDate("data_perdido").toLocalDate());
            it.setDataAchado(rs.getDate("data_achado").toLocalDate());
            it.setDataDevolvido(rs.getDate("data_devolvido").toLocalDate());
            it.setLocal(rs.getString("local"));
            it.setDescricao(rs.getString("descricao"));
            it.setLugarAchado(rs.getString("lugar_achado"));
            it.setLugarPerdido(rs.getString("lugar_perdido"));
            it.setFoto(rs.getString("foto"));
            it.setStatus(StatusItem.values()[rs.getInt("status")]);
            itens.add(it);
        }

        return itens;
    }

    /**
     * Retorna todos os itens do banco de dados
     * @return List<Item> itens
     */
    public List<Item> getItens() {
        try {
            Connection conn = getConnection();

            String sql = "SELECT * FROM Item;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            List<Item> itens = getItemPorRs(rs);

            conn.close();
            return itens;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> getItemPorNome(String nome) {
        try {
            Connection conn = getConnection();

            String sql = "SELECT * FROM Item WHERE nome LIKE ? ORDER BY status LIMIT 15;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            List<Item> itens = getItemPorRs(rs);

            conn.close();
            return itens;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> getItemPorPeriodo(LocalDate inicio, LocalDate fim) {
        try {
            Connection conn = getConnection();

            String sql = "SELECT i FROM Item i WHERE i.data BETWEEN ? AND ? LIMIT 45;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(inicio));
            stmt.setDate(2, Date.valueOf(fim));

            ResultSet rs = stmt.executeQuery();

            List<Item> itens = getItemPorRs(rs);

            conn.close();
            return itens;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna uma lista com os 5 itens perdidos prováveis de corresponderem a um achado
     * @param achado item achado
     * @return possíveis itens perdidos
     */
    public List<Item> getPerdidosPorSimilaridade(Item achado) {
        String similaridadePerdido = "SELECT *," +
                "(similarity(descricao, ?) * 0.5 + \n" +
                "similarity(nome, ?) * 0.3 + \n" +
                "similarity(lugar_perdido, ?) * 0.15) AS score\n" +
                "FROM Item \n" +
                "WHERE status = 1 AND score > 0.80 ORDER BY match_score DESC LIMIT 1;";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(similaridadePerdido);
            stmt.setString(1, achado.getDescricao());
            stmt.setString(2, achado.getNome());
            stmt.setString(3, achado.getLugarAchado());

            List<Item> itens = getItemPorRs(stmt.executeQuery());
            conn.close();

            return itens;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna uma lista com 5 itens achados que podem corresponder a um item perdido
     * @param perdido item perdido a ser procurado
     * @return possíveis itens achados
     */
    public List<Item> getAchadosPorSimilaridade(Item perdido) {
        String similaridadePerdido = "SELECT *," +
                "(similarity(descricao, ?) * similarity(nome, ?)) AS score " +
                "FROM Item WHERE status = 1 AND cor = ? ORDER BY score DESC LIMIT 5;";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(similaridadePerdido);
            stmt.setString(1, perdido.getDescricao());
            stmt.setString(2, perdido.getNome());
            stmt.setString(3, perdido.getLugarAchado());

            List<Item> itens =  getItemPorRs(stmt.executeQuery());
            conn.close();
            return itens;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reduz a quantidade de cores disponíveis, fazendo com que variações mínimas de cores
     * escolhidas não interfiram no item encontrado
     * @param cor Cor no formato `#HHHHHH`
     * @return número inteiro de 0-511 representando cores
     */
    public static Integer reduzirEspectroCor(String cor) {
        String h = cor.substring(1);
        int grande = Integer.parseInt(h, 16);
        int vermelho = ((grande & 0xFF0000) >> 16) / 32;
        int verde = ((grande & 0x00FF00) >> 8) / 32;
        int azul = (grande & 0x0000FF) / 32;

        return (vermelho << 16) | (verde << 8) | azul;
    }

    public static String reverterCor(Integer cor) {
        int vermelho = Integer.min(((cor & 0b111000000) >> 6) * 32, 255);
        int verde = Integer.min(((cor & 0b000111000) >> 3) * 32, 255);
        int azul = Integer.min((cor & 0b000000111) * 32, 255);

        String svermelho = String.format("%02x", vermelho);
        String sverde = String.format("%02x", verde);
        String sazul = String.format("%02x", azul);

        return "#" + svermelho + sverde + sazul;
    }


}
