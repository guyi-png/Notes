package com.guyi.JUC;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculate extends RecursiveTask {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0, 1000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }

    private long start;
    private long end;
    private static final long THRESHOLD = 99L;   //邻接值

    public ForkJoinSumCalculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        long sum = 0;
        if (length <= THRESHOLD){
            for (long i =start; i <= end; i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
            right.fork();
            return (Long) left.join() + (Long) right.join();
        }
    }
}
