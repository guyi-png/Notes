package com.factory.abstractMethod;


public class LDCheesePizza extends Pizza {
    @Override
    protected void prepare() {
        super.name = "伦敦的奶酪披萨";
        System.out.println("伦敦的奶酪披萨 准备");
    }
}
