package br.cefetmg.inf.sigap.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public final class ItemService {
    /**
     * Factory para geração de entities para transações do banco de dados
     */
    private static final EntityManagerFactory emFac =
            Persistence.createEntityManagerFactory("itemunit");


    public static void adicionarItemPerdido(String nome, Date data, String descricao, String lugar, String foto) {
        Item perdido = ItemFactory.criarItemPerdido(nome, data, descricao, lugar, foto);
        EntityManager em = emFac.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(perdido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    public void close() {
        emFac.close();
    }
}
