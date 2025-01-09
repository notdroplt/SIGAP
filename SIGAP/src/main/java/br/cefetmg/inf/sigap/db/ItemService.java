package br.cefetmg.inf.sigap.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public final class ItemService {
    /**
     * Factory para geração de entities para transações do banco de dados
     */
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    private ItemService() {

    }

    public static void adicionarItemPerdido(String nome, Date data, String descricao, String lugar, String foto) {
        Item perdido = ItemFactory.criarItemPerdido(nome, data, descricao, lugar, foto);

        Session s = sessionFactory.openSession();
        try {
            s.getTransaction().begin();
            s.persist(perdido);
            s.getTransaction().commit();
        } catch (Exception e) {
            if (s.getTransaction().isActive())
                s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    public static List getItemPorNome(String nome) {
        EntityManager em = sessionFactory.createEntityManager();
        return em.createQuery("SELECT item from Item item WHERE Item.nome LIKE :nome")
                .setParameter("nome", nome)
                .setMaxResults(15)
                .getResultList();
    }

    public void close() {
        sessionFactory.close();
    }
}
