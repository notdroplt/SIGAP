package br.cefetmg.inf.sigap.db;

/**
 *
 * @author luisg
 */

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioService {

    private static RelatorioService instance;
    private final ItemService itemService;

    private RelatorioService() {
        this.itemService = ItemService.getInstance();
    }

    public static synchronized RelatorioService getInstance() {
        if (instance == null) {
            instance = new RelatorioService();
        }
        return instance;
    }

    public Map<String, Integer> getQuantidadeItensPorLocal() {
        List<Item> itens = itemService.getItens();
        Map<String, Integer> quantidadePorLocal = new HashMap<>();

        for (Item item : itens) {
            String local = item.getLocal() != null ? item.getLocal() : "Desconhecido";
            quantidadePorLocal.put(local, quantidadePorLocal.getOrDefault(local, 0) + 1);
        }

        return quantidadePorLocal;
    }

    public Map<String, Integer> getQuantidadeItensPorStatus() {
        List<Item> itens = itemService.getItens();
        Map<String, Integer> quantidadePorStatus = new HashMap<>();

        for (Item item : itens) {
            String status = item.getStatus() != null ? item.getStatus().name() : "Desconhecido";
            quantidadePorStatus.put(status, quantidadePorStatus.getOrDefault(status, 0) + 1);
        }

        return quantidadePorStatus;
    }

    public Map<String, Integer> getQuantidadeItensPorPeriodo(String inicio, String fim) {
        List<Item> itensPorPeriodo = itemService.getItemPorPeriodo(LocalDate.parse(inicio), LocalDate.parse(fim));
        Map<String, Integer> quantidadePorPeriodo = new HashMap<>();

        for (Item item : itensPorPeriodo) {
            String local = item.getLocal() != null ? item.getLocal() : "Desconhecido";
            quantidadePorPeriodo.put(local, quantidadePorPeriodo.getOrDefault(local, 0) + 1);
        }

        return quantidadePorPeriodo;
    }

    public List<Item> getItensPorNome(String nome) {
        return itemService.getItemPorNome(nome);
    }
}

