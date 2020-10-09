package com.jpa.test;

import com.jpa.entity.Customer;
import com.jpa.entity.Order;
import org.hibernate.annotations.QueryHints;

import javax.persistence.*;
import java.util.List;

/**
 * JPQL：  java persistence query language
 * JPQL是面向对象的查询，而不是面向数据库表的查询，
 * 因此在jpql语句中的对象名必须与实体类的类名一致，严格区分大小写。
 */
public class JPQL {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("myPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

//        testJPQL(manager);
//        testPartlyProperties(manager);
//        testNamedQuery(manager);
//        testNativeQuery(manager);
//        testQueryCache(manager);
//        testOrderBy(manager);
//        testGroupBy(manager);
//        testLeftOuterJoin(manager);
//        testSubQuery(manager);
        testExecuteUpdate(manager);

        transaction.commit();
        manager.close();
        factory.close();
    }

    // 使用JPQL完成update操作或delete操作
    private static void testExecuteUpdate(EntityManager manager) {
        String jpql = "update Customer c set c.name = ?1 where c.id = ?2";
        Query query = manager.createQuery(jpql);
        query.setParameter(1, "真由理").setParameter(2, 2);
        query.executeUpdate();
    }

    private static void testSubQuery(EntityManager manager) {
        String jpql = "select o from Order o where o.customer = " +
                "(select c from Customer c where c.name=?1)";
        Query query = manager.createQuery(jpql);
        query.setParameter(1, "爱莉");
        List<Order> orders = query.getResultList();
        System.out.println(orders);
    }

    // jpql中的 fetch 将查询到的多个order放到customer
    private static void testLeftOuterJoin(EntityManager manager) {
        String jpql = "select c from  Customer c left outer join fetch c.orders where c.id = ?1";
        Query query = manager.createQuery(jpql);
        query.setParameter(1, 2);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer.getOrders());
    }

    private static void testGroupBy(EntityManager manager) {
        String jpql = "select o.customer from Order o group by o.customer having count(*) >= 2";
        Query query = manager.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }

    // 使用order by desc 降序
    private static void testOrderBy(EntityManager manager) {
        String jpql = "from Customer c where c.age > ?1 order by c.age desc";

        Query query = manager.createQuery(jpql);
        query.setParameter(1, 10);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer);
    }

    // 在persistence.xml文件中配置use_query_cache为true
    private static void testQueryCache(EntityManager manager) {
        String jpql = "from Customer c where c.age > ?1";

        Query query = manager.createQuery(jpql).setHint(QueryHints.CACHEABLE, true);
        query.setParameter(1, 10);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer);

        query = manager.createQuery(jpql).setHint(QueryHints.CACHEABLE, true);
        query.setParameter(1, 10);
        Customer customer1 = (Customer) query.getSingleResult();
        System.out.println(customer == customer1);
    }

    // 直接使用本地sql语句查询
    private static void testNativeQuery(EntityManager manager) {
        String sql = "select name from customers where id = ?";
        Query query = manager.createNativeQuery(sql);
        query.setParameter(1, 2);
        String result = (String) query.getSingleResult();
        System.out.println(result);
    }

    // 在实体类中有@NamedQuery可以使用createNamedQuery方法
    private static void testNamedQuery(EntityManager manager) {
        Query query = manager.createNamedQuery("testNamedQuery");
        query.setParameter(1, 2);
        Customer customer = (Customer)query.getSingleResult();
        System.out.println(customer);
    }

    /**
     * 默认情况下， 若只查询部分属性，则将返回Object[]类型的结果， 或者Object[]类型的list,
     * 在jpql语句中 可以把查询记录封装到指定对象中
     */
    private static void testPartlyProperties(EntityManager manager) {
        String jpql = "select new Customer(c.name, c.age) from Customer c where c.id = ?1";
        Query query = manager.createQuery(jpql);
        query.setParameter(1, 2);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer.getName());
        System.out.println(customer.getBirth());
    }

    private static void testJPQL(EntityManager manager) {
        String jpql = "from Customer c where c.age > ?1";
        Query query = manager.createQuery(jpql);
        query.setParameter(1, 15);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }
}
