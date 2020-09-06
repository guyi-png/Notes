package com.strategy;

public abstract class Duck {
    // 聚合策略接口
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;
    protected SwimBehavior swimBehavior;

    public void quack(){
        quackBehavior.quack();
    }

    public void swim(){
        swimBehavior.swim();
    }

    public void fly(){
        flyBehavior.fly();
    }

    public abstract void display();
}
