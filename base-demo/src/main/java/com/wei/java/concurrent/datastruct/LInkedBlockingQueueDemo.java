package com.wei.java.concurrent.datastruct;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue只是保证线程安全，take和put像生产者消费者那样等待
 * 容量超大
 */
public class LInkedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
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
                    queue.add("element" + i);
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
                    String element = queue.remove();
                    SystemOutUtil.println("finish get " + i + " element " + element);
                }
            }
        });

    }
}
