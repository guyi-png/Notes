package com.factory.method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工厂方法模式： 定义一个创建对象的抽象方法，
 * 由子类决定要实例化的类，工厂方法模式将对象的实例化推迟到子类
 *
 * 订购披萨
 * 此时该类充当工厂方法， 子类实现地区差异
 */
public abstract class OrderPizza {

    // 由子类重写
    protected abstract Pizza createPizza(String type);

    public void getPizza(){
        Pizza pizza = null;
        do {
            String type = getType();
            pizza = createPizza(type);

            if (pizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else{
                System.out.println("没有");
                break;
            }
        }while (true);
    }

    private String getType(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入披萨种类");
        String type = null;
        try {
            type = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return type;
    }
}
