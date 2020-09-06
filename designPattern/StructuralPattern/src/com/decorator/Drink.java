package com.decorator;

/**
 * 被包装类的抽象父类
 */
public abstract class Drink {
    private String desc;
    private double price = 0;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // 抽象方法，计算cost的方法
    protected abstract double cost();
}
