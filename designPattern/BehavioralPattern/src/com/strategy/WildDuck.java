package com.strategy;

public class WildDuck extends Duck {
    public WildDuck (){
        super.quackBehavior = new GaGaQuack();
        super.flyBehavior = new GoodFly();
        super.swimBehavior = new GoodSwim();
    }

    @Override
    public void display() {
        System.out.println("俺是野鸭");
        this.quack();
        this.fly();
        this.swim();
    }
}
