package com.builder;

/**
 * 客户需要 房子
 */
public class Client {
    public static void main(String[] args) {
        // 客户需要普通的房子
        HouseBuilder houseBuilder = new CommonHouse(); // 建造普通的房子
        // 搞来一个 指挥者
        HouseDirector houseDirector = new HouseDirector();
        houseDirector.setHouseBuilder(houseBuilder);
        // 完成房子的建造， 返回房子产品
        House house = houseDirector.constructHouse();
        System.out.println(house);
    }
}
