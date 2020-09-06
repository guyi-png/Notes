package com.builder;

/**
 * builder（抽象的建造者）
 */
public abstract class HouseBuilder {
    // 组合关系成员
    House house = new House();

    // 写好建造的流程，具体由子类来实现
    // 打地基
    public abstract void buildBase();
    // 砌墙
    public abstract void buildWalls();
    // 盖房顶
    public abstract void roofed();

    // 建造房子，并返回House产品
    public House buildHouse(){
        return house;
    }
}
