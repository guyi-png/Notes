package com.spring.useXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {
    private static final ApplicationContext ctx = new
            ClassPathXmlApplicationContext("transaction.xml");

    private static final BookShopService bookShopService
            = (BookShopService)ctx.getBean("bookShopService");

    private static final Cashier cashier
            = (Cashier) ctx.getBean("cashier");

    public static void main(String[] args) {
//        bookShopService.purchase("小冲", "1000");
        cashier.checkout("小冲", Arrays.asList("1000", "1001"));
    }
}
