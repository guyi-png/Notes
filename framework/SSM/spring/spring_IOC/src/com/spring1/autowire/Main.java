package com.spring1.autowire;

import com.spring1.beansRelation.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("autowire.xml");
        Person person = (Person) ctx.getBean("person");

        System.out.println(person.getAddress());
        System.out.println(person.getCar());
    }
}
