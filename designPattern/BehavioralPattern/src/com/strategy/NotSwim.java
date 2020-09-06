package com.strategy;

public class NotSwim implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("俺不会游泳");
    }
}
