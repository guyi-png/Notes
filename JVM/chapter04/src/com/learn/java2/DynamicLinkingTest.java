package com.learn.java2;

public class DynamicLinkingTest {
    int num;

    public void method(){
        System.out.println("method");
    }

    public void method2(){
        System.out.println("method2");
        method();
        num++;
    }
}
