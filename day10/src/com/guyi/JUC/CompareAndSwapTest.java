package com.guyi.JUC;

/**
 * CAS算法,大致
 */
public class CompareAndSwapTest {

    public static void main(String[] args) {
        final CompareAndSwapTest c = new CompareAndSwapTest();

        for (int i =0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expect = c.get();
                    boolean b = c.compareAndSet(expect, (int) (Math.random() * 10));
                    System.out.println(b);
                }
            }).start();
        }
    }

    private int value;

    private synchronized int get(){
        return value;
    }

    private synchronized int compareAndSwap(int expect, int newValue){
        int oldValue = value;
        if (oldValue == expect){
            value = newValue;
        }
        return oldValue;
    }

    private synchronized boolean compareAndSet(int expect, int newValue){
        return expect == compareAndSwap(expect, newValue);
    }
}
