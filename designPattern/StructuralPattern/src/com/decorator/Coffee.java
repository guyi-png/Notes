package com.decorator;

public class Coffee extends Drink {

    @Override
    protected double cost() {
        return super.getPrice();
    }
}
