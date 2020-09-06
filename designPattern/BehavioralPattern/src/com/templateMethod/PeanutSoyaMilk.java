package com.templateMethod;

/**
 * 花生豆浆
 */
public class PeanutSoyaMilk extends SoyaMilk{

    @Override
    protected void add() {
        System.out.println("添加花生原料");
    }
}
