package com.templateMethod;

/**
 * 黑豆豆浆
 */
public class BlackBeanSoyaMilk extends SoyaMilk{
    @Override
    protected void add() {
        System.out.println("添加黑豆原料");
    }
}
