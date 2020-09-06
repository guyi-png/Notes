package com.factory.abstractMethod;


public class BJPepperPizza extends Pizza {

    @Override
    protected void prepare() {
        super.name = "北京的胡椒披萨";
        System.out.println("北京的胡椒披萨 准备");
    }
}
