package com.wei.java.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by viruser on 2019/10/16.
 */
public class BlockingQueueMain {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(17);
        threadPoolExecutor.execute(new BlockingQueueConsumerRunnable(queue));
        threadPoolExecutor.execute(new BlockingQueueProviderRunnable(queue));
        threadPoolExecutor.shutdown();
    }
}
