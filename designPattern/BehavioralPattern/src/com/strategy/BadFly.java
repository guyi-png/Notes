package com.strategy;

public class BadFly implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("俺能飞一会");
    }
}
