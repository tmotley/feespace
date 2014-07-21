package com.feestudio;


import com.feestudio.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(TENANT_ID_KEY, "intel");
        EntityManager entityManager = ctx.getBean(EntityManagerFactory.class).createEntityManager();
        entityManager.setProperty(TENANT_ID_KEY, "intel");
        Client clientA = new Client(new Integer(1), "Apple");
        saveClient(entityManager, clientA);

        entityManager = ctx.getBean(EntityManagerFactory.class).createEntityManager();
        entityManager.setProperty(TENANT_ID_KEY, "intel");
        Client clientB = getClient(entityManager, new Integer(1));
        System.out.println(clientA.getName().equals(clientB.getName()));
    }

    @Transactional
    static void saveClient(EntityManager entityManager, Client client) {
        entityManager.persist(client);
    }

    @Transactional
    static Client getClient(EntityManager entityManager, Integer clientId) {
        Client client = entityManager.find(Client.class, clientId);
        return client;
    }
}
