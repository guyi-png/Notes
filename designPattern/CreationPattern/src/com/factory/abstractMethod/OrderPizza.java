package com.factory.abstractMethod;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    public OrderPizza(Factory factory){
        this.factory = factory;
    }

    private final Factory factory;

    public void getPizza(){
        Pizza pizza = null;

        do {
            String type = getType();
            pizza = factory.createPizza(type);

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
