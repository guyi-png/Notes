package com.strategy;

public class GoodFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("俺很会飞");
    }
}
