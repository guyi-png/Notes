package com.comparer.learn;

import java.io.Console;

/**
 *其他类的方法
 */
public class OtherClass {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(time);

        Console console = System.console();
        System.out.println(console);

        String javaHome = System.getProperty("java.version");
        System.out.println(javaHome);

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        String userName = System.getProperty("user.name");
        System.out.println(userName);
// Math.xxx
        //BigInteger and BigDecimal ......


















        System.exit(0); //正常的退出
        System.out.println("看把你能的,哈哈哈");
    }
}
