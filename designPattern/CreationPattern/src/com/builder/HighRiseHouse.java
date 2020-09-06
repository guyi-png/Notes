package com.builder;

/**
 * 高楼：concreteBuilder（具体实现建造者）
 */
public class HighRiseHouse extends HouseBuilder{
    {
        house.setBase("50米的地基");
        house.setWalls("50cm厚的墙");
        house.setRooftop("透明的房顶");
    }

    @Override
    public void buildBase() {
        System.out.println("高楼打"+ house.getBase());
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌"+ house.getWalls());
    }

    @Override
    public void roofed() {
        System.out.println("高楼盖"+house.getRooftop());
    }
}
