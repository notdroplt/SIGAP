package br.cefetmg.inf.sigap.factories;

import br.cefetmg.inf.sigap.dto.Item;
import br.cefetmg.inf.sigap.dto.StatusItem;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {
    @Test
    void itemAchado() {
        LocalDate data = LocalDate.now();
        Item achadoFactory = ItemFactory.criarItemAchado(
                "nome",
                0,
                "marca",
                data,
                "local",
                "descricao",
                "lugar",
                "foto");

        assertAll("propriedades",
                () -> assertNull(achadoFactory.getUid()),
                () -> assertEquals("nome", achadoFactory.getNome()),
                () -> assertEquals(0, achadoFactory.getCor()),
                () -> assertEquals("marca", achadoFactory.getMarca()),
                () -> assertNull(achadoFactory.getDataPerdido()),
                () -> assertEquals(data, achadoFactory.getDataAchado()),
                () -> assertNull(achadoFactory.getDataDevolvido()),
                () -> assertEquals("local", achadoFactory.getLocal()),
                () -> assertEquals("descricao", achadoFactory.getDescricao()),
                () -> assertEquals("lugar", achadoFactory.getLugarAchado()),
                () -> assertNull(achadoFactory.getLugarPerdido()),
                () -> assertEquals("foto", achadoFactory.getFoto()),
                () -> assertEquals(StatusItem.ENCONTRADO, achadoFactory.getStatus())
        );
    }

    @Test
    void itemPerdido() {
        LocalDate data = LocalDate.now();
        Item perdidoFactory = ItemFactory.criarItemPerdido(
                0L,
                "nome",
                0,
                "marca",
                data,
                "descricao",
                "lugar",
                "local",
                "foto");

        assertAll("propriedades",
                () -> assertEquals(0, perdidoFactory.getUid()),
                () -> assertEquals("nome", perdidoFactory.getNome()),
                () -> assertEquals(0, perdidoFactory.getCor()),
                () -> assertEquals("marca", perdidoFactory.getMarca()),
                () -> assertEquals(data, perdidoFactory.getDataPerdido()),
                () -> assertNull(perdidoFactory.getDataAchado()),
                () -> assertNull(perdidoFactory.getDataDevolvido()),
                () -> assertEquals("local", perdidoFactory.getLocal()),
                () -> assertEquals("descricao", perdidoFactory.getDescricao()),
                () -> assertNull(perdidoFactory.getLugarAchado()),
                () -> assertEquals("lugar", perdidoFactory.getLugarPerdido()),
                () -> assertEquals("foto", perdidoFactory.getFoto()),
                () -> assertEquals(StatusItem.PERDIDO, perdidoFactory.getStatus())
        );
    }
}