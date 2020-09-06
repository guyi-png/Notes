package com.builder;

/**
 * 普通房子： concreteBuilder（具体实现建造者）
 */
public class CommonHouse extends HouseBuilder{
    {
        house.setBase("5米的地基");
        house.setWalls("10cm厚的墙");
        house.setRooftop("常规房顶");
    }

    @Override
    public void buildBase() {
        System.out.println("普通房子打"+house.getBase());
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子砌"+house.getWalls());
    }

    @Override
    public void roofed() {
        System.out.println("普通房子盖"+house.getRooftop());
    }
}
