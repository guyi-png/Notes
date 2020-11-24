package com.learn.java;

/**
 * 方法的结束方式分为2种，以return为正常结束，方法执行中出现未捕获处理的异常，以抛出异常的方式结束
 */
public class StackFrameTest {
    public static void main(String[] args) {            // 先是 main 栈帧
        method1();
    }

    public static void method1() {             // 在main栈帧上，入栈 method1 栈帧
        System.out.println("method1 start");
        method2();
        System.out.println("method1 end");
        System.out.println(10/0);
    }

    public static void method2() {         // 在method1栈帧上，入栈 method2 栈帧
        System.out.println("method2 start");
        method3();
        System.out.println("method2 end");
    }

    public static void method3() {     // 在method2栈帧上，入栈 method3 栈帧
        System.out.println("method3 start");

        System.out.println("method3 end");
    }
}
