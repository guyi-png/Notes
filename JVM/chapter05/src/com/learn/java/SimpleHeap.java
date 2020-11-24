package com.learn.java;

/**
 * -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小，
 * -X 是jvm的参数   ms是memory start
 *
 * -Xmx 用来设置堆空间的最大内存大小
 *
 * 查看设置的参数值的方式：
 *      jps     查看进程号
 *      jstat -gc 进程
 *
 *      或者
 *      -XX:+PrintGCDetails
 *
 *
 * -XX:NewRatio 设置新生代与老年代的比例，默认为2
 *
 *
 * -XX:-UseAdaptiveSizePolicy: 关闭自适应的内存分配策略（-）
 *
 */
public class SimpleHeap {
    public static void main(String[] args) {
        SimpleHeap simpleHeap = new SimpleHeap();
        SimpleHeap simpleHeap1 = new SimpleHeap();

        int[] arr = new int[6];

        Object[] objs = new Object[3];

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
