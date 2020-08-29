package com.spring1.aop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("aop-config.xml");
        Calculator calculator = (Calculator) ctx.getBean("calculator");
        int div = calculator.div(100, 5);
        System.out.println("div="+div);
    }
}
