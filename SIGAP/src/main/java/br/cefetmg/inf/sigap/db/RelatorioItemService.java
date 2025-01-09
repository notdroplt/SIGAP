package br.cefetmg.inf.sigap.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.List;

public final class RelatorioItemService {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            config.configure("persistence.xml");

            StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties());

            sessionFactory = config.buildSessionFactory(serviceRegistry.build());
        } catch (Throwable ex) {
            System.err.println("Erro ao inicializar o Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static List<Item> listarItens() {
        EntityManager em = sessionFactory.createEntityManager();
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

    public static List<Item> listarItensPorPeriodo(String dataInicio, String dataFim) {
        EntityManager em = sessionFactory.createEntityManager();
        return em.createQuery(
                "SELECT i FROM Item i WHERE i.data BETWEEN :inicio AND :fim", Item.class)
                .setParameter("inicio", dataInicio)
                .setParameter("fim", dataFim)
                .getResultList();
    }

    public void close() {
        sessionFactory.close();
    }
}
