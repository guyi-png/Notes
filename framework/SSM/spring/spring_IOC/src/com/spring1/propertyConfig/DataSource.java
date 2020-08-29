package com.spring1.propertyConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

public class DataSource {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring-config.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Properties properties = dataSource.getProperties();
        System.out.println(properties.getProperty("user"));

        System.out.println("------------------------------");

        ApplicationContext context = new
                ClassPathXmlApplicationContext("beans-properties.xml");
        DataSource dataSource1 = (DataSource) context.getBean("dataSource1");
        System.out.println(dataSource1.properties.getProperty("username"));
    }

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "properties=" + properties +
                '}';
    }
}
