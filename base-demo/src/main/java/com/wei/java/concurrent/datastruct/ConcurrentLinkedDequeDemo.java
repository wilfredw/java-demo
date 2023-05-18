package com.wei.java.concurrent.datastruct;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();
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
                    queue.offerFirst("element" + i);
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
                    String element = queue.pollFirst();
                    SystemOutUtil.println("finish get " + i + " element " + element);
                }
            }
        });

    }
}
