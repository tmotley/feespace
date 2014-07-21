package com.feestudio;


import com.feestudio.domain.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * User: tom
 * Date: 7/20/14
 * Time: 3:28 PM
 */

public class Test {
    public static final String TENANT_ID_KEY = "tenant-id";
    private static ClientRepo clientRepo;
/* entityManager = ctx.getBean(EntityManagerFactory.class).createEntityManager();
        entityManager.setProperty(TENANT_ID_KEY, "yahoo");*/

    public static void main(String[] args) throws Exception {
        // Retrieve new EM instance from CTX
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        EntityManager entityManager = ctx.getBean(EntityManagerFactory.class).createEntityManager();

        // Set tenant-id on EM
        entityManager.setProperty(TENANT_ID_KEY, "pixar");

        // Create and save new enttity
        Integer id = new Integer(new Object().hashCode());
        Client clientA = new Client(id, "foobar");
        saveClient(entityManager, clientA);

        // test retrieval
        Client clientB = getClient(entityManager, id);
        System.out.println(clientA.getName().equals(clientB.getName()));

        // changing tenant context by instantiating a new EM should shield previous tenant-id
        entityManager = ctx.getBean(EntityManagerFactory.class).createEntityManager();
        entityManager.setProperty(TENANT_ID_KEY, "yahoo");

        // test retrieval again...
        clientB = getClient(entityManager, id);
        System.out.println(clientB == null);
    }

    @Transactional
    static void saveClient(EntityManager entityManager, Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Transactional
    static Client getClient(EntityManager entityManager, Integer clientId) {
        Client client = entityManager.find(Client.class, clientId);
        return client;
    }
}
