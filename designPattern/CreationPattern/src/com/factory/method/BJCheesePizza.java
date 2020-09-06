package com.factory.method;

public class BJCheesePizza extends Pizza {

    @Override
    protected void prepare() {
        super.name = "北京的奶酪披萨";
        System.out.println("北京的奶酪披萨 准备材料");
    }
}
