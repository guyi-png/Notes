package com.learn.java;

/**
 * 命令行 到java编译后的字节码文件目录（idea默认out/production）
 * 执行   javap -v StackStructureTest.class   反编译，
 *
 * 反编译：机器码(汇编语言) → 高级编程语言
 * 但是通常不能把可执行文件变成高级语言源代码。
 *
 * jps打印进程
 */
public class StackStructureTest {
    public static void main(String[] args) {
        int i = 9 + 1;
        int k = 10;
        int q = i + k;
    }
}
