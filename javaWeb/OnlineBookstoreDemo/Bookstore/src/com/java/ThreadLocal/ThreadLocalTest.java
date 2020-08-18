package com.java.ThreadLocal;

/**
 * 通过ThreadLocal.set()将对象的引用保存到各线程的自己一个map(ThreadLocalMap)中
 * 每个线程都有这样一个map，执行ThreadLocal.get()时，各线程从自己的map中
 * 取出放进去的那个对象，ThreadLocal实例是作为map的key来使用
 *
 * 注： ThreadLocal不是用来解决共享对象的多线程访问问题的
 *
 * 应用场景：
 *  按线程多实例的对象访问
 *  适用于各个线程依赖不同的变量值完成操作的场景
 */
public class ThreadLocalTest implements Runnable{
    public static void main(String[] args) {
        ThreadLocalTest tlt = new ThreadLocalTest();

        new Thread(tlt, "A线程").start();
        new Thread(tlt, "B线程").start();
    }


    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        for (int i =0; i < 10; i++){
            threadLocal.set(Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" : "+threadLocal.get());
        }
    }
}
