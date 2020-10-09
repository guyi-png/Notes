package com.jpa.test;

import com.jpa.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Persistence类时用于获取EntityManagerFactory实例的
 * EntityManagerFactory接口主要用来创建EntityManager实例
 * EntityManager实例的一些方法：find(), getReference(), persist(), remove()
 * merge()方法： 用于处理Entity的同步。即数据库的插入和更新操作
 * flush()方法： 同步持久化上下文环境，即将未保存的状态信息保存到数据库中
 * refresh()方法： 用数据库实体记录的值 更新实体对象的状态
 *
 *
 * 在JPA规范中, EntityManager是完成持久化操作的核心对象,实体作为普通Java对象,
 * 只有在调用EntityManager将其持久化后才会变成持久化对象.
 * EntityManager对象在一组实体类与底层数据源之间进行O/R映射的管理,它可以用来管理和更新Entity Bean,
 * 根据主键查找Entity Bean,还可以通过JPQL语句查询实体
 * 实体的状态:
 *      新建状态:新创建的对象,尚未拥有持久性主键.
 *      持久化状态:已经拥有持久性主键并和持久化建立了上下文环境
 *      游离状态:拥有持久化主键,但是没有与持久化建立上下文环境
 *      删除状态:拥有持久化主键,已经和持久化建立上下文环境,但是从数据库中删除
 *
 * EntityTransaction对象的一些方法:
 *  begin(), commit(), rollback(), isActive()
 */
public class Main {
    public static void main(String[] args) {
        // 创建EntityManagerFactory
        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        // 创建EntityManager
        EntityManager manager = managerFactory.createEntityManager();
        // 开启事务
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();



//        testPersist(manager);
//        testFind(manager);
//        testGetReference(manager);
//        testRemove(manager);
//        testMerge(manager);
        testFlushAndRefresh(manager);

        // 提交事务
        transaction.commit();

        // 关闭资源
        manager.close();
        managerFactory.close();
    }

    /**
     *  同hibernate的flush()方法和refresh()方法一样
     */
    private static void testFlushAndRefresh(EntityManager manager) {
        Customer customer = manager.find(Customer.class, 2);
        System.out.println(customer);

        manager.refresh(customer);

        customer.setAge(16);

        manager.flush();
    }

    /**
     * 类似于hibernate的session.saveOrUpdate()方法，
     * 若传入的是一个临时对象,把临时对象的属性复制到新的对象中，然后对新的对象执行持久化（insert）操作，
     * 所以原来的对象没有id，但新的对象有id
     * 若传入的是一个游离对象，即该对象有oid，jpa会查询对应的记录，然后会把游离对象的属性赋值到查询到的对象中
     * 再对查询到的对象执行update操作
     */
    private static void testMerge(EntityManager manager) {
        Customer customer = new Customer("苏芳", "sufang@flower.com", 17, new Date());
        Customer customer1 = manager.merge(customer);

        System.out.println(customer);
        System.out.println(customer1);
        System.out.println(customer==customer1);    //false
    }

    /**
     * 类似于hibernate的session.delete()方法，把对象对应的记录从数据库中移除，但只能移除持久化对象
     */
    private static void testRemove(EntityManager manager) {
        Customer customer = manager.find(Customer.class,1);

        manager.remove(customer);
    }

    /**
     * 类似于hibernate的session.load()方法
     */
    private static void testGetReference(EntityManager manager) {
        Customer customer = manager.getReference(Customer.class, 1);
        System.out.println(customer.getClass().getName());//com.jpa.entity.Customer$HibernateProxy$135nPwQX
        System.out.println(customer);
    }

    /**
     * 类似于hibernate的session.get()方法
     */
    private static void testFind(EntityManager manager) {
        Customer customer = manager.find(Customer.class, 1);
        System.out.println(customer);
    }

    /**
     * 类似于hibernate的session.save()方法，使临时对象变为持久化对象状态，但自给id抛异常
     */
    private static void testPersist(EntityManager manager) {
        // 进行持久化的操作
        Customer customer = new Customer("爱莉", "lovebaba@77.com", 16, new Date());
        manager.persist(customer);
    }
}
