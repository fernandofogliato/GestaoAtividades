package br.com.fogliato.model.persistence.dao.producer;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestaoAtividades");

    @Produces
    @SessionScoped
    public EntityManager entityManager() {
        return factory.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
