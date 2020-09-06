package com.strategy;

public class JiJiQuack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("叽叽叽");
    }
}
