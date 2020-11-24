package com.learn.java;

/**
 * 栈溢出异常,
 *  默认栈空间大小，以count计数为： 15259，
 *  在vm option设置栈内存大小： -Xss256k后
 *  count为 3076
 */
public class StackOverFlow {
    private static int count = 1;

    public static void main(String[] args) {
        method();
    }

    private static void method() {
        System.out.println(count++);
        method();
    }
}
