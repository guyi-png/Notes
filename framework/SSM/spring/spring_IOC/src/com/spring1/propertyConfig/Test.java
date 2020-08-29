package com.spring1.propertyConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring-config.xml");
        Test test = (Test)ctx.getBean("test");
        test.printMessage();
    }

    private String message;
    private int count;

    public Test(String message, int count) {
        this.message = message;
        this.count = count;
    }

    public void printMessage(){
        for (int i=0; i < count; i++){
            System.out.println(message);
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Test{" +
                "message='" + message + '\'' +
                ", count=" + count +
                '}';
    }
}
