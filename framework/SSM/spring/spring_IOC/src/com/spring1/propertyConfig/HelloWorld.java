package com.spring1.propertyConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
    public static void main(String[] args) {
//        HelloWorld helloWorld = new HelloWorld();
//        helloWorld.setName("World");
        // 使用spring
        // 去spring-config.xml配置该类的bean，
        // 创建spring的ioc容器对象, 配置文件中的类也被一起创建(其属性也一起被设置)
        ApplicationContext ctx  = new
                ClassPathXmlApplicationContext("spring-config.xml");
        // 从ioc容器中获取bean实例, getBean(arg)这的arg只是起核实(或者说匹配)的作用，用于获取对应的bean
        // 通过配置中的标识id获取
        HelloWorld helloWorld = (HelloWorld)ctx.getBean("helloWorld");
        // 通过类类型获取,要求ioc容器中只有一个该类型的实例
        HelloWorld helloWorld1 = ctx.getBean(HelloWorld.class);

        helloWorld.hello();

    }

    private String name;

    // 必须有空参的构造器
    public HelloWorld(){
        System.out.println("HelloWorld's Constructor");
    }

    public void setName(String name){
        System.out.println("setName");
        this.name = name;
    }

    public void hello(){
        System.out.println("hello "+name);
    }
}
