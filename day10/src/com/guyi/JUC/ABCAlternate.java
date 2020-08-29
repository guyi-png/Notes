package com.guyi.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 开启3个线程，这三个线程的id分别为A，B，C，每个线程将自己的id打印10遍，输出显示的如
 * ABCABCABCABC...  每个字母都出现10次
 */
public class ABCAlternate {
    public static void main(String[] args) {
        Alternate al = new Alternate();
        int count = 10;

        for (int i =1; i <= 3; i++){
            if (i == 1){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i =0; i < count; i++){
                            al.loopA();
                        }
                    }
                }, "A").start();
            }
            if (i == 2){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i =0; i < count; i++){
                            al.loopB();
                        }
                    }
                }, "B").start();
            }
            if (i == 3){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i =0; i < count; i++){
                            al.loopC();
                        }
                    }
                }, "C").start();
            }
        }

    }
}

class Alternate{
    private int number = 1; //当前执行的线程的标记

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(){
        lock.lock();
        try{
            if (number != 1){
                condition1.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 2;
            condition2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void loopB(){
        lock.lock();
        try{
            if (number != 2){
                condition2.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 3;
            condition3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();
        try{
            if (number != 3){
                condition3.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 1;
            condition1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
