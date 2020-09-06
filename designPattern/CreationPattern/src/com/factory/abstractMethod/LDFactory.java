package com.factory.abstractMethod;

/**
 * 实现工厂类, 伦敦的工厂
 */
public class LDFactory extends Factory{

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)){
            pizza = new LDCheesePizza();
        }else if ("pepper".equals(type)){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
