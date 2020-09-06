package com.strategy;

public class GoodSwim implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("俺很会游泳");
    }
}
