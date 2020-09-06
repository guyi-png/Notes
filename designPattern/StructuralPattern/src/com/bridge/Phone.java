package com.bridge;

/**
 * Phone 做桥
 */
public abstract class Phone {
    // 聚合关系
    private Brand brand;

    public Phone (Brand brand){
        this.brand = brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        brand.open();
    }

    protected void close(){
        brand.close();
    }

    protected void call(){
        brand.call();
    }
}
