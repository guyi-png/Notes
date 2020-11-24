package com.learn.java1;

public class ClassLoaderTest1 {
    public static void main(String[] args) {
        // 获取类加载器的方式
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader);
        System.out.println(contextClassLoader);
        System.out.println(systemClassLoader);

    }
}
