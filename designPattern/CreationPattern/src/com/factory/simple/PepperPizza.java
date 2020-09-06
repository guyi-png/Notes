package com.factory.simple;

public class PepperPizza extends Pizza{

    @Override
    protected void prepare() {
        super.name = "胡椒披萨";
        System.out.println("准备胡椒披萨");
    }
}
