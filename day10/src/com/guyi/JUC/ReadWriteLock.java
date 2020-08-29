package com.guyi.JUC;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写锁
 * Write and Write/ Read and Write需要互斥
 * Read and Read不需要互斥
 */
public class ReadWriteLock {
    private static int number = 0;
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                set((int)(Math.random()*100));
            }
        }, "write").start();

        for (int i =0; i < 100; i++){
            new Thread(ReadWriteLock::print, "read").start();
        }

    }

    public static void print(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+ ":"+ number);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

    public static void set(int num){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            number = num;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
