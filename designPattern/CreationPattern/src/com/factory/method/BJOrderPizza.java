package com.factory.method;


public class BJOrderPizza extends OrderPizza{
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")){
            pizza = new BJCheesePizza();
        }else if (type.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }

}
