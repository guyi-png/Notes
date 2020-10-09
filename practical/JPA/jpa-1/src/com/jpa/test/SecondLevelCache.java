package com.jpa.test;

import com.jpa.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 二级缓存
 */
public class SecondLevelCache {
    private static final EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("myPersistenceUnit");
    private static EntityManager manager = factory.createEntityManager();
    private static EntityTransaction transaction = manager.getTransaction();

    public static void main(String[] args) {
        transaction.begin();

        testFactoryCache();

        transaction.commit();
        manager.close();
        factory.close();
    }

    private static void testFactoryCache() {
        Customer customer = manager.find(Customer.class, 2);

        transaction.commit();
        manager.close();

        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();

        Customer customer1 = manager.find(Customer.class, 2);
        System.out.println(customer == customer1);
    }
}
