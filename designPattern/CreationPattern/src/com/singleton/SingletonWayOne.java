package com.singleton;

/**
 * 单例模式: 所谓类的单例设计模式，就是采取一定的方法保证整个的软件系统中，对某个类只能
 * 存在一个对象实例， 并且该类只提供一个取得其对象实例的静态方法
 * 单例模式有八种方式：
 *      饿汉式（静态常量）
 *      饿汉式（静态代码块）
 *      懒汉式（线程不安全）
 *      懒汉式（线程安全， 同步方法）
 *      懒汉式（线程安全， 同步代码块）
 *      双重检查
 *      静态内部类
 *      枚举
 *
 *  单例模式注意事项和细节说明：
 *      单例模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，
 *      创建对象时耗时过多或耗费资源过多，但又经常使用的对象，
 *      工具类对象，
 *      频繁访问数据库或文件的对象(如数据源，sessionFactory)
 *      使用单例模式可以提高系统的性能
 *
 */

/**
 *  饿汉式（静态常量）
 *      优点： 这种写法比较简单，就是在类装载的时候就完成实例化，避免了线程同步问题
 *      缺点： 在类装载时就完成实例化，没有达到Lazy Loading的效果，
 *      如果从始至终都没有使用这个实例，则会造成内存的浪费
 *
 *      jdk中java.lang.Runtime 用到了此方式
 */
public class SingletonWayOne {
    // 声明私有构造器
    private SingletonWayOne (){}

    // 静态常量 实例
    private static final SingletonWayOne instance = new SingletonWayOne();

    public static SingletonWayOne getInstance(){
        return instance;
    }

    public void printMessage(){
        System.out.println("饿汉式（静态常量）");
    }
}
