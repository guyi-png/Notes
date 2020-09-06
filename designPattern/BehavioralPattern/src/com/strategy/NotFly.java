package com.strategy;

public class NotFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("俺不会飞");
    }
}
