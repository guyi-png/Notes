package com.jpa.test;

import com.jpa.entity.Category;
import com.jpa.entity.Customer;
import com.jpa.entity.Item;
import com.jpa.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Set;

public class MappingRelation {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("myPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

//        testManyToOnePersist(manager);
//        testManyToOneFind(manager);
//        testManyToOneRemove(manager);
//        testManyToOneUpdate(manager);
//        testOneToManyPersist(manager);
//        testOneToManyFind(manager);
//        testOneToManyRemove(manager);
//        testOneToManyUpdate(manager);
//        testManyToOnManyPersist(manager);
        testManyToManyFind(manager);

        transaction.commit();
        manager.close();
        factory.close();
    }

    // 对于关联的集合，默认使用懒加载的策略
    // 使用维护关联关系的一方还是被维护关联关系的一方，其find后的sql语句相同
    private static void testManyToManyFind(EntityManager manager) {
        Category category = manager.find(Category.class, 1);
        System.out.println(category);
        System.out.println(category.getItems());
    }

    private static void testManyToOnManyPersist(EntityManager manager) {
        Item item = new Item();
        item.setItemName("abc");
        Item item1 = new Item();
        item1.setItemName("def");
        Category category = new Category();
        category.setCategoryName("ABC");
        Category category1 = new Category();
        category1.setCategoryName("DEF");
        // 发生关系
        item.getCategories().add(category);
        item.getCategories().add(category1);
        item1.getCategories().add(category);
        item1.getCategories().add(category1);
        category.getItems().add(item);
        category.getItems().add(item1);
        category1.getItems().add(item);
        category1.getItems().add(item1);

        // 持久化操作
        manager.persist(item);
        manager.persist(item1);
        manager.persist(category);
        manager.persist(category1);
    }

    private static void testOneToManyUpdate(EntityManager manager) {
        Customer customer = manager.find(Customer.class, 2);
        customer.getOrders().iterator().next().setOrderName("OBC");
    }

    // 默认情况下，若删除一的一方，则会先把关联的多的一方的外键置空，然后删除，
    // 通过在oneToMany注解中添加属性cascade(级联), cascade=CascadeType.REMOVE
    // 在删除一方对应的行也会删除多中对应外键的行
    private static void testOneToManyRemove(EntityManager manager) {
        Customer customer = manager.find(Customer.class, 1);
        manager.remove(customer);
    }

    /*
    默认oneToMany的多的一方使用懒加载策略
     */
    private static void testOneToManyFind(EntityManager manager) {
        Customer customer = manager.find(Customer.class, 2);
        System.out.println(customer);

        System.out.println(customer.getOrders());
    }

    private static void testOneToManyPersist(EntityManager manager) {
        Customer customer = new Customer("爱莉", "aili@baba.com", 16, new Date());
        // 发生多对一关系
        Order order1 = new Order("ABC", customer);
        Order order2 = new Order("DEF", customer);
        Set<Order> orders = customer.getOrders();
        // 发生一对多关系
        orders.add(order1);
        orders.add(order2);
        // 持久化操作
        manager.persist(customer);
        manager.persist(order1);
        manager.persist(order2);
    }

    private static void testManyToOneUpdate(EntityManager manager) {
        Order order = manager.find(Order.class, 5);
        order.getCustomer().setAge(15);
    }

    private static void testManyToOneRemove(EntityManager manager) {
        Order order = manager.find(Order.class, 6);
        manager.remove(order);
        // 因为customer有外键引用表的对应主键，该删除操作报错
//        Customer customer = manager.find(Customer.class, 1);
//        manager.remove(customer);
    }

    /*
    默认情况下，使用左外连接的方式来获取多的一方对象和其关联的一的一方的对象，
    可设置@ManyToOne的fetch属性指定加载策略
     */
    private static void testManyToOneFind(EntityManager manager) {
        Order order = manager.find(Order.class, 2);
        System.out.println(order.getOrderName());

        System.out.println(order.getCustomer());
    }

    /**
     * 保存(persist)操作时建议先保存ManyToOne中的one的一方， 再保存many的一方，这样就省去过多update语句
     */
    private static void testManyToOnePersist(EntityManager manager) {
        Customer customer = new Customer("爱莉", "aili@baba.com", 16, new Date());
        // 发生关系
        Order order1 = new Order("ABC", customer);
        Order order2 = new Order("DEF", customer);
        // 持久化操作
        manager.persist(customer);
        manager.persist(order1);
        manager.persist(order2);
    }
}
