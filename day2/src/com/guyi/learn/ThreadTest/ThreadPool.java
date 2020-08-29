package com.guyi.learn.ThreadTest;

import java.util.concurrent.*;

/**
 * 创建线程池的方式：
 *      使用线程池
 *      使用线程池的好处:
 *          1.减少在创建和销毁线程上所花的时间以及系统资源的开销
 *          2.如不使用线程池，有可能造成系统创建大量线程而导致消耗完系统内存以及”过度切换”。
 *          3.便于线程的管理
 *
 * 线程池体系结构：
 * java.util.concurrent.Executor: 负责线程池的使用与调度的根接口
 *      |--- ExecutorService 子接口，线程池的主接口
 *          |--- ThreadPoolExecutor 实现类
 *          |--- ScheduledExecutorService 子接口，用与线程的调度
 *              |--- ScheduledThreadPoolExecutor 实现类，继承了ThreadPoolExecutor，实现了ScheduleExecutorService
 *
 * 工具类： Executors
 * ExecutorService newFixedThreadPool(): 创建固定大小的线程池
 * ExecutorService newCachedThreadPool(): 缓存线程池，可以根据需求自动改变大小
 * ExecutorService newSingleThreadExecutor(): 创建单个线程池，线程池只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool(): 创建固定大小的线程池，可以延迟或定时执行任务
 */
public class ThreadPool {
    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //设置线程池的属性
        ThreadPoolExecutor executor = (ThreadPoolExecutor)executorService;
        executor.setCorePoolSize(10);
        executor.setKeepAliveTime(10, TimeUnit.MINUTES);  // ...

        //2.通过提供实现Runnable或Callable接口的实现类的对象，告诉线程池需要执行的代码，并执行
        executor.execute(new NumberThread());  //适用于Runnable
        Future future = executor.submit(new Number1Thread());//适用于Callable

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //3.关闭线程池
        executorService.shutdown();


        // 使用newScheduledThreadPool()
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(6);
        ScheduledFuture<String> result = pool.schedule(() -> {
            System.out.println("奥利给");
            return "奥利给";
        }, 3, TimeUnit.SECONDS);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}

class NumberThread implements Runnable{
    @Override
    public void run() {
        for (int i =0; i<= 100; i++){
            if (i % 2 ==0){
                System.out.println(Thread.currentThread().getName() +":"+ i);
            }
        }
    }
}

class Number1Thread implements Callable{
    @Override
    public Object call() {
        for (int i =0; i<= 100; i++){
            if (i % 100 ==0){
                System.out.println(Thread.currentThread().getName() +":"+ i+ " haha");
            }
        }
        return "haha";
    }
}