package com.strategy;

public class ToyDuck extends Duck {
    public ToyDuck (){
        super.quackBehavior = new GuGuQuack();
        super.flyBehavior = new NotFly();
        super.swimBehavior = new GoodSwim();
    }

    @Override
    public void display() {
        System.out.println("俺是玩具鸭");
        this.quack();
        this.fly();
        this.swim();
    }
}
