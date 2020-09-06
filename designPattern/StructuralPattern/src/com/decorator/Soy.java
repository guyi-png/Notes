package com.decorator;

public class Soy extends Decorator {

    public Soy(Drink drink) {
        super(drink);
        setDesc("豆浆调料");
        setPrice(1.0);
    }
}
