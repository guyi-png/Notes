package com.learn.java1;

public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i =0; i < 1000000; i++){
            alloc();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    private static void alloc() {
        Object obj = new Object();
    }
}
