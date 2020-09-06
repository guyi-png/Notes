package com.strategy;

public class PekingDuck extends Duck{
    public PekingDuck (){
        super.quackBehavior = new JiJiQuack();
        super.flyBehavior = new NotFly();
        super.swimBehavior = new NotSwim();
    }

    @Override
    public void display() {
        System.out.println("俺是北京烤鸭");
        this.quack();
        this.fly();
        this.swim();
    }
}
