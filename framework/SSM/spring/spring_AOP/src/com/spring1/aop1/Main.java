package com.spring1.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring-config.xml");
        Calculator calculator = (Calculator) ctx.getBean("calculator");
        int add = calculator.add(1, 4);
        System.out.println("add"+add);
        int div = calculator.div(3, 1);
        System.out.println("div:"+div);
        int mul = calculator.mul(23, 56);
        System.out.println("mul:"+mul);
    }
}
