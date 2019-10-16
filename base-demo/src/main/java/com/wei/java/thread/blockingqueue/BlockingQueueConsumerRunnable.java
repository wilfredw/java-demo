package com.wei.java.thread.blockingqueue;

import com.wei.java.util.SystemOutUtil;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by wangwei on 2019/10/16.
 */
public class BlockingQueueConsumerRunnable implements Runnable {
    private ArrayBlockingQueue<Integer> queue;
    public BlockingQueueConsumerRunnable(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        SystemOutUtil.println("BlockingQueueConsumerRunnable start");
        for(int i = 0; i < 15; ++i) {
            try {
                SystemOutUtil.println("BlockingQueueConsumerRunnable start get " + i);
                Integer value = queue.take();
                SystemOutUtil.println("BlockingQueueConsumerRunnable finish get " + i + " value: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
                SystemOutUtil.println("BlockingQueueConsumerRunnable interrupted get " + i);
            }
        }
        SystemOutUtil.println("BlockingQueueConsumerRunnable finish");
    }
}
