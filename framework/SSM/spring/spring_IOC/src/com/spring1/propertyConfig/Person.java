package com.spring1.propertyConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Person {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring-config.xml");
        // 此时会将person依赖的Test引用一起配置
        Person person = (Person) ctx.getBean("person3");

        List<Test> tests = person.getTests();
        System.out.println(tests);
        Map<Integer, Test> testMap = person.getTestMap();
        System.out.println(testMap);
    }

    private String name;
    private int age;
    private Test test;
    private List<Test> tests;
    private Map<Integer, Test> testMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Map<Integer, Test> getTestMap() {
        return testMap;
    }

    public void setTestMap(Map<Integer, Test> testMap) {
        this.testMap = testMap;
    }
}
