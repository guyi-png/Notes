package com.factory.abstractMethod;

/**
 * 实现工厂类, 北京的工厂
 */
public class BJFactory extends Factory{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if ("cheese".equals(type)){
            pizza = new BJCheesePizza();
        }else if ("pepper".equals(type)){
            pizza = new BJPepperPizza();
        }

        return pizza;
    }
}
