package com.wei.java.concurrent.datastruct;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueSimpleDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

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
}
