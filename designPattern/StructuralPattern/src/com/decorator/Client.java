package com.decorator;

/**
 * 装饰者设计模式
 *      动态的将新功能附加到对象上，在对象功能扩展方面，它比继承更有弹性，也体现了ocp原则
 *
 *
 *      jdk中io模块的
 *      InputStream 相当于 这的 Drink
 *      FileInputStream， StringInputStream等 相当于 这的 LongBlack, ShortBlack等
 *      FilterInputStream就是一个装饰类, 内部聚合了 InputStream接口
 *      BufferedInputStream, DataInputStream等 相当于 这的 调味品
 */
public class Client {
    public static void main(String[] args) {
        // 买了 longBlack 咖啡
        Drink drink = new LongBlack();
        // 再 加了 巧克力调味品
        Decorator decorator = new Chocolate(drink);

        System.out.println(decorator.getDesc());
        System.out.println("价格"+decorator.cost());

        System.out.println("---------------------");
        // 又加了 豆浆调味品
        Soy drink1 = new Soy(decorator);

        System.out.println(drink1.getDesc());
        System.out.println("价格"+drink1.cost());
    }
}
