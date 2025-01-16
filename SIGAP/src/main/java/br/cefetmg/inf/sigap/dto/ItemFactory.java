package br.cefetmg.inf.sigap.dto;

import java.time.LocalDate;

/**
 * Classe Factory para geração de itens perdidos ou achados
 *
 * @version 0.1
 * @author Arthur Tolomelli
 */
public final class ItemFactory {
    /**
     * Cria um item que foi perdido
     * @param nome nome do item perdido
     * @param data data em que foi perdido
     * @param descricao detalhamento do item
     * @param lugarPerdido local em que possivelmente está
     * @param foto foto desse item ou similar
     * @return Item criado com status perdido
     */
    public static Item criarItemPerdido(Long uid, String nome, LocalDate data, String descricao, String lugarPerdido, String campus, String foto) {
        return new Item(uid, nome, data, null, null, campus,
                descricao, null, lugarPerdido, foto, StatusItem.PERDIDO);
    }

    /**
     * Cria um item que foi encontrado
     * @param nome nome do item encontrado
     * @param data data em que foi encontrado
     * @param local local em que está guardado
     * @param descricao descrição do item
     * @param lugarAchado local em que foi encontrado
     * @param foto foto do item encontrado
     * @return Item criado com status encontrado
     */
    public static Item criarItemAchado(String nome, LocalDate data, String local, String descricao, String lugarAchado, String foto) {
        return new Item(null, nome, null, data, null, local,
                descricao, lugarAchado, null, foto, StatusItem.ENCONTRADO);
    }
}
