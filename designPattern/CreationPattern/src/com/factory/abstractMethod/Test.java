package com.factory.abstractMethod;


/**
 * 工厂模式的意义：
 *      将实例化对象的代码提取出来，放到一个类中统一管理和维护，达到和主项目的依赖关系的解耦
 *      从而提高项目的扩展性，维护性
 *      创建对象实例时，不要直接 new 类， 而是把这个 new 类的动作放到一个工厂方法中，并返回
 *
 *      变量不要直接持有具体类的引用
 *      不要让类直接继承具体类， 而是继承抽象类或者实现接口
 *      不要覆盖基类中已经实现的方法
 *
 *
 * 定义了一个接口用于创建相关或依赖关系的对象蔟，而无需指明具体类
 * 抽象方法可以将简单工厂模式和工厂方法模式进行整合
 * 从设计层面看，抽象工厂就是对简单工厂模式的进一步抽象，
 * 将工厂抽象成两层，抽象工厂和具体实现的工厂子类，可以根据创建对象类型使用对应的工厂子类。
 */
public class Test {
    public static void main(String[] args) {
        OrderPizza orderPizza = new OrderPizza(new LDFactory());
        orderPizza.getPizza();
    }
}
