package com.factory.simple;

public class CheesePizza extends Pizza{

    @Override
    protected void prepare() {
        super.name = "奶酪披萨";
        System.out.println("准备奶酪披萨");
    }
}
