package com.decorator;

public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setDesc("巧克力调料");
        setPrice(2.0);
    }
}
