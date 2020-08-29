package com.spring1.beansRelation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans-relation.xml");
        Address address1 = (Address) ctx.getBean("address1");
        System.out.println(address1);
        Address address2 = (Address) ctx.getBean("address2");
        System.out.println(address2);

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }
}
