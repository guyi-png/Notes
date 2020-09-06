package com.builder;

/**
 * director（指挥者）
 */
public class HouseDirector {
    // 聚合关系成员
    private HouseBuilder houseBuilder = null;

    // 利用setter存入HouseBuilder的不同实现类
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // 指挥者处理如何建造房子
    public House constructHouse(){
        // 指挥  先打地基
        houseBuilder.buildBase();
        // 再砌墙
        houseBuilder.buildWalls();
        // 最后盖房顶
        houseBuilder.roofed();
        // 完事就返回 House对象
        return houseBuilder.buildHouse();
    }
}
