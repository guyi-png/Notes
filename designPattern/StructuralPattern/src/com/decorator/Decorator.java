package com.decorator;

/**
 * 装饰者  聚合和继承 Drink
 */
public abstract class Decorator extends Drink{
    private Drink drink;

    public Decorator(Drink drink){
        this.drink = drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    protected double cost() {
        // 自己的价格 + coffee的价格
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        return drink.getDesc() + " :\n" + drink.cost()+" \n"
                + super.getDesc() + " : \n" + super.getPrice();
    }
}
