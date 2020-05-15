package com.guyi.learn;

import java.util.concurrent.*;

/**
 * 创建线程池的方式：
 *      使用线程池
 *      使用线程池的好处:
 *          1.减少在创建和销毁线程上所花的时间以及系统资源的开销
 *          2.如不使用线程池，有可能造成系统创建大量线程而导致消耗完系统内存以及”过度切换”。
 *          3.便于线程的管理
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
        executorService.execute(new NumberThread());  //适用于Runnable
        executorService.submit(new Number1Thread());  //适用于Callable

        //3.关闭线程池
        executorService.shutdown();
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