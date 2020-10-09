package com.hibernate.test;

import com.hibernate.mapper.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

/**
 * Configuration： 负责管理 hibernate 的配置信息，包括 连接数据库的url，jdbc驱动类，用户名和密码
 * 数据库连接池等
 * 创建Configuration的两种方式：
 *      - 属性文件（hibernate.properties）
 *          Configuration cfg = new Configuration();
 *      - xml文件(hibernate.cfg.xml)
 *          Configuration cfg = new Configuration().configure();
 *
 * SessionFactory接口：一般情况下一个应用中只初始化一个SessionFactory，其用于生成session
 *
 * Session接口： session是应用程序与数据库之间交互操作的一个单线程对象，是hibernate的运行中心，
 * 所有持久化对象必须在session的管理下才可以进行持久化操作。该对象的生命周期很短
 * session对象有一级缓存，在显式使用flush()方法之前所有的持久化操作的数据都存在session对象处。
 * 它提供了基本的保存，更新，删除和加载java对象的方法.
 */
public class Main {
    public static void main(String[] args) {
        // hibernate的任何配置和服务都需要在serviceRegistry对象中注册后才能生效
        // 默认读取 configure("hibernate.cfg.xml")
        // configures settings from hibernate.cfg.xml
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        // 创建一个session对象
        Session session = sessionFactory.openSession();

        // 开启事务
        Transaction transaction = session.beginTransaction();



        //  保存数据
//        testSave(session);
        // 查询数据
        testGet(session);



        // 提交事务
        transaction.commit();

        // 关闭资源
        session.close();
        sessionFactory.close();
    }

    private static void testGet(Session session) {
        News news = session.get(News.class, 1);
        System.out.println(news);
        News news1 = session.get(News.class, 1);
        System.out.println(news == news1);  //true

        news.setAuthor("劳斯基"); //修改数据后,在commit方法调用之前底层调用了flush()方法，改变对象属性时也改变的数据库表的字段的值
        // flush()方法使数据库表中的记录和 session缓存中的对象的状态保存一致。为保持一致，会执行一些sql语句
        // 可以显式使用session.flush()
        // 与flush()相反 refresh()会强制发送select语句，以使session缓存中对象的状态和 数据库表中对应的记录保存一致;(注意数据库的隔离级别)
        // clear()就是直接清理缓存
        System.out.println(news1);
    }

    private static void testSave(Session session){
        session.save(new News("杠精是怎么炼成的?", "托尔夫", new Date()));
    }
}
