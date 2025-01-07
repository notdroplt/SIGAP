package br.cefetmg.inf.sigap.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public final class ItemService {
    /**
     * Factory para geração de entities para transações do banco de dados
     */
    private static final SessionFactory sessionFactory;


    static {
        try {
            Configuration config = new Configuration();
            config.configure("persistence.xml");

            StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties());

            sessionFactory = config.buildSessionFactory(serviceRegistry.build());
        } catch (Throwable ex) {
            System.err.println("erro no hibernate :O");
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static void adicionarItemPerdido(String nome, Date data, String descricao, String lugar, String foto) {
        Item perdido = ItemFactory.criarItemPerdido(nome, data, descricao, lugar, foto);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                System.out.println("Salvando");
                session.persist(perdido);
                System.out.println("salvo");

                tx.commit();
                System.out.println("commit");
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }
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
