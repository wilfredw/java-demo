package com.wei.java.thread.blockingqueue;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by viruser on 2019/10/16.
 */
public class BlockingQueueProviderRunnable implements Runnable {
    private ArrayBlockingQueue<Integer> queue;
    public BlockingQueueProviderRunnable(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        SystemOutUtil.println("BlockingQueueProviderRunnable start");
        for(int i = 0; i < 20; ++i) {
            try {
                SystemOutUtil.println("BlockingQueueProviderRunnable start put " + i);
                queue.put(Integer.valueOf(i));
                SystemOutUtil.println("BlockingQueueProviderRunnable finish put " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                SystemOutUtil.println("BlockingQueueProviderRunnable interrupted put " + i);
            }
        }
        SystemOutUtil.println("BlockingQueueProviderRunnable finish");
    }
}
