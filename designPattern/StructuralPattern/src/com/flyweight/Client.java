package com.flyweight;

/**
 * 享元模式：
 *      也叫蝇量模式。运用共享技术有效的支持大量细粒度的对象
 *      常用于系统底层开发， 解决系统的性能问题，像数据库连接池，里面都是创建好的连接对象，
 *      在这些连接对象中有我们需要的，则创建一个
 *      享元模式能够解决重复对象的内存浪费的问题，当系统中有大量相似对象， 需要缓冲池时，
 *      无需总是创建对象，可以从缓冲池里拿，这样可以降低系统内存，同时提高效率
 *
 *      享元模式常用于池技术，string常量池， 数据库连接池， 缓冲池等都是享元模式的应用
 *
 *      内部状态：指对象共享出来的信息， 存储在享元对象内部且不会随着环境改变而改变
 *      外部状态： 指对象得以依赖的一个标记，是随环境而变化的
 *
 *      在jdk中Integer类使用了享元模式
 *      Integer.valueOf()相同值时，其地址相同
 *      -128 ~ 127范围内，使用享元模式返回
 *
 *      享元模式提高了系统的复杂度，需要注意
 */
public class Client {
    public static void main(String[] args) {
        WebsiteFactory factory = new WebsiteFactory();
        Website website = factory.getWebSiteCategory("新闻");
        website.use(new User("撒旦"));

        Website website1 = factory.getWebSiteCategory("博客");
        website1.use(new User("达广"));

        Website website2 = factory.getWebSiteCategory("博客");
        website2.use(new User("mike"));

        Website website3 = factory.getWebSiteCategory("博客");
        website3.use(new User("士大夫"));

        Website website4 = factory.getWebSiteCategory("博客");
        website4.use(new User("时光"));

        System.out.println(factory.getWebSiteCount());
    }
}
