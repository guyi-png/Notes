package com.guyi.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 闭锁, 在完成某些运算时，只有其他线程都完成后才运行
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);

        long start = System.currentTimeMillis();
        LatchTest latchTest = new LatchTest(latch);
        for (int i =0; i < threadCount; i++){
            new Thread(latchTest).start();
        }
        // 等待
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("************");
        System.out.println((end - start));
    }
}

class LatchTest implements Runnable{
    private CountDownLatch latch;

    public LatchTest(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {

        synchronized(this){
            try {
                for (int i =0; i < 10000; i++){
                    System.out.println(i);
                }
            } finally {
                latch.countDown(); //线程减1
            }
        }
    }
}
