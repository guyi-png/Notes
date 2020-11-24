package com.learn.java;

/**
 * 安装 jclasslib， binary viewer查看二进制源码，
 *  class文件在文件开头有特定的文件标识：  CA FE BA BE
 */
public class HelloLoader {
    private static int a = 1;  // 在类加载子系统linking的准备阶段，a的值为0

    static {
        a = 10;  // prepare a=0 -> initial a=1 -> a=10
        b = 8;   // prepare b =0 -> initial b=8 -> b=9
//        System.out.println(b);  // 非法的向前引用
    }

    private static int b = 9;

    public static void main(String[] args) {
        System.out.println("ClassLoader来加载我");
        System.out.println(HelloLoader.a);
        System.out.println(b);
    }
}
