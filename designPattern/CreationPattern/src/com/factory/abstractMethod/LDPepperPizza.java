package com.factory.abstractMethod;

public class LDPepperPizza extends Pizza {

    @Override
    protected void prepare() {
        super.name = "伦敦的胡椒披萨";
        System.out.println("伦敦的胡椒披萨 准备");
    }
}
