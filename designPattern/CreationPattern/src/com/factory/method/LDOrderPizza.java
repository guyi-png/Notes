package com.factory.method;

public class LDOrderPizza extends OrderPizza{

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")){
            pizza = new LDPepperPizza();
        }else if (type.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
