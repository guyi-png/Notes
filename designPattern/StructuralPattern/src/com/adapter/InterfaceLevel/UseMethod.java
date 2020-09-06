package com.adapter.InterfaceLevel;

public class UseMethod {
    public static void main(String[] args) {
        // 匿名继承抽象类
        AllImplements one = new AllImplements() {
            // 需要使用那个方法，就只覆盖(重写)那个方法
            @Override
            public void one() {
                System.out.println("one方法");
            }
        };

        one.one();

    }
}
