package com.factory.simple;

public class GreekPizza extends Pizza {
    @Override
    protected void prepare() {
        super.name = "希腊披萨";
        System.out.println("准备希腊披萨");
    }
}
