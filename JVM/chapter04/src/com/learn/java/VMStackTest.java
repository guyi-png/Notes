package com.learn.java;

/**
 * 在栈中，先是main（对应的栈帧）入栈，其栈帧定义了i和o变量...
 * 再是method入栈，其栈帧定义了j和i变量...
 * method执行完后先出栈，
 * 再执行main方法后出栈
 *
 */
public class VMStackTest {
    public static void main(String[] args) {
        int i = 19;
        int o = 18;

        method();
    }

    private static void method() {
        int j = 12;
        int i = 66;
    }

}
