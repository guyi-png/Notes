package com.factory.simple;

/**
 * 简单工厂, 也可以考虑使用静态
 *      java.util.Calendar使用了简单工厂
 */
public class SimpleFactory {
    private SimpleFactory (){}
    private static volatile SimpleFactory instance;
    public static SimpleFactory getInstance(){
        if (instance == null){
            synchronized (SimpleFactory.class){
                if (instance == null){
                    instance = new SimpleFactory();
                }
            }
        }
        return  instance;
    }

    // 根据披萨类型，返回对应的实例
    public Pizza createPizza(String type){
        Pizza pizza = null;

        System.out.println("使用简单工厂模式");

        if (type.equals("greek")){
            pizza = new GreekPizza();
        }else if (type.equals("cheese")){
            pizza = new CheesePizza();
        }else if (type.equals("pepper")){
            pizza = new PepperPizza();
        }

        return pizza;
    }
}
