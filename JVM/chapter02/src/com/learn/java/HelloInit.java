package com.learn.java;

/**
 * 查看class字节码文件
 */
public class HelloInit {
    private int a = 2;

    public HelloInit(){
        a  =10;
        int c = 8;
    }

    static class Parent{
        private static int k = 19;
    }
    static class Son extends Parent{
        private static int k;
    }

    public static void main(String[] args) {
        int b = 2;
        // 加载Parent类，其次加载Son类
        System.out.println(Son.k);  //0
    }
}
