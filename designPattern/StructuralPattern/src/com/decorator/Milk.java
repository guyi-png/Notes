package com.decorator;

public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶调料");
        setPrice(3.0);
    }
}
