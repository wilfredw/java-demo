package com.wei.java.thread.pool.logstats;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LogStatsDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
        printStats(threadPool);


        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {
                threadPool.submit(() -> {
                    SystemOutUtil.println("=== " + id + " start");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SystemOutUtil.println("=== " + id + " finish");
                });
            } catch (Exception e) {
                e.printStackTrace();
                SystemOutUtil.println("=== error while submit task");
                atomicInteger.decrementAndGet();
            }
        });

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("final : " + atomicInteger.intValue());

    }

    public static void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            SystemOutUtil.println("===============");
            SystemOutUtil.println("Pool Size: " + threadPool.getPoolSize()
                    + " Active Threads: " + threadPool.getActiveCount()
                    + " Completed Task Num: " + threadPool.getCompletedTaskCount()
                    + " In Queue Task Num: " + threadPool.getQueue().size());
        }, 0, 1, TimeUnit.SECONDS);
    }
}
