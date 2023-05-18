package com.wei.java.concurrent.datastruct;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue只是保证线程安全，并不是像生产者消费者那样等待
 * 容量超大
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 10, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>()
        );
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("start put i " + i);
                    try {
                        queue.put("element" + i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("finish put i" + i);
                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("start get " + i);
                    String element = null;
                    try {
                        element = queue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("finish get " + i + " element " + element);
                }
            }
        });

    }
}
