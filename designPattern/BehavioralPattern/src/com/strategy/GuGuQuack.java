package com.strategy;

public class GuGuQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("咕咕咕");
    }
}
