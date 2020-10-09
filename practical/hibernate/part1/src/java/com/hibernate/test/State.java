package com.hibernate.test;

import com.hibernate.mapper.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * 持久化对象的状态：
 * 临时对象（Transient）： oid为null，不处于session的缓存中，在数据库中没有对应的记录
 * 持久化对象（Persist）：oid不为null，位于session缓存中，持久化对象和数据库中的相关记录对应，
 * session在flush缓存时，会根据持久化对象的属性变化，来同步更新数据库，在同个session实例的缓存中，
 * 数据库表中的每条记录只对应唯一的持久化对象
 * 删除对象（Removed）： 在数据库中没有和其oid对应的记录，不再处于session缓存中，应用程序一般不再使用被删除的对象
 * 游离对象（detached）： oid不为null，不处于session缓存中，游离对象是由持久化对象转变过来的，再数据库中可能还存在对应的记录
 */
public class State {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new MetadataSources(
                new StandardServiceRegistryBuilder()
                .configure().build()
        ).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//
//        testSave(session);
//        testPersist(session);
//        testGet(session);
//        testLoad(session);
//        testUpdate(session);
//        testSaveOrUpdate(session);
//        testDelete(session);
//        testEvict(session);
//        testDoWork(session);
//

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    /**
     *  session.get()与 session.load()比较：
     *  1. 执行get方法会立即加载对象，而执行load方法在不使用获取的对象时不会立即执行查询操作，
     *  而是返回一个代理对象。 可以说get是立即检索，而load是延迟检索
     *
     *  2. 若数据库中没有对应的行记录，get方法会返回null，而load方法会报错
     *
     *  3. load方法可能会抛出LazyInitializationException, 如load后的代理对象 要去查数据库之前session就已经close了
     */
    private static void testGet(Session session) {
        News news = session.get(News.class, 500);
        System.out.println(news);
    }

    private static void testLoad(Session session) {
        News news = session.load(News.class, 500);
        System.out.println(news.getClass().getName());//com.hibernate.mapper.News$HibernateProxy$ShlFIFLO
        System.out.println(news);
    }

    /**
     * session.save()方法使一个临时对象变成一个持久化对象
     * 会为对象分配id(oid)，但是一开始自己指定的id无效，数据库表中以hibernate分配的id为准，
     * 而且不能在save后改它分配的id。
     * 在flush缓存时会发送一条insert语句
     */
    private static void testSave(Session session){
        News news = new News("Atk", "ok", new Date());
        news.setId(1000);

        System.out.println(news);  //News{id=null, title='Atk', author='ok', date=Tue Sep 22 20:13:29 CST 2020}
        session.save(news);
        System.out.println(news);  //News{id=5, title='Atk', author='ok', date=Tue Sep 22 20:13:29 CST 2020}
    }

    /**
     * session.persist()方法也会执行insert语句
     * 但是在persist方法之前就给id赋值了，将会报错
     */
    private static void testPersist(Session session) {
        News news = new News("Asd", "7k7k", new Date());
//        news.setId(999);

        session.persist(news);
    }

    /**
     * session.update()方法
     * 1. 若更新一个持久化对象，不需要显式的调用update方法，因为在commit方法之前，会执行flush方法
     *
     * 2. 若更新一个游离对象，需要显式的调用 update方法, 该方法可以把一个游离对象为持久化对象
     *
     * 3. 无论要更新的游离对象和数据库表的记录是否一致，都会发送update语句，
     * 为避免update方法盲目的发送update语句，可以在更新之前查询一下，
     * 在 .hbm.xml映射文件中配置 select-before-update=true, 但通常不需要设置该属性
     *
     * 4. 若数据库表中没有对应的记录，但调用了update方法，将会抛异常
     *
     * 5. 当update方法关联一个游离对象时，如果在session的缓存中已经存在相同的oid的持久化对象，将会抛异常（oid只对应一个对象）
     */
    private static void testUpdate(Session session) {
        News news = session.get(News.class, 1);
        news.setAuthor("亚历山大");
        session.update(news);
    }

    /**
     * session.saveOrUpdate()方法会根据 临时对象和游离对象分别执行save()和update()
     * 1. 若oid不为空，但数据库表中没有和其对应的行记录， 会抛异常
     * 2. oid值等于id为unsaved-value属性值的对象，也被认为是一个游离对象
     */
    private static void testSaveOrUpdate(Session session) {
        News news = new News("sk", "abc", new Date());
        news.setId(1);
        session.saveOrUpdate(news);
    }

    /**
     * session.delete()方法执行删除操作，只要oid和数据库表中的一条记录对应，就会准备执行给delete操作，
     * 若oid在数据库表中没有对应的记录，就会抛异常
     *
     * 可以通过设置hibernate配置文件 设置hibernate.use_identifier_rollback为true 使删除对象后，把其oid
     * 置为null
     */
    private static void testDelete(Session session) {
        News news = session.get(News.class, 1);
        session.delete(news);
        System.out.println(news);
    }

    /**
     * session.evict()方法让指定的持久化对象从缓存中移除
     */
    private static void testEvict(Session session) {
        News news1 = session.get(News.class, 7);
        News news2 = session.get(News.class, 8);
        news1.setTitle("A");
        news2.setTitle("B");

        session.evict(news2);
    }

    private static void testDoWork(Session session){
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                System.out.println(connection); //获取原生的connection对象
            }
        });
    }
}
