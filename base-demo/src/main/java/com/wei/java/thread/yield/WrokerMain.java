package com.wei.java.thread.yield;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WrokerMain {
    public static void main(String[] args) {
        WorkerA workerA = new WorkerA();
        WorkerB workerB = new WorkerB();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        executor.execute(workerA);
        executor.execute(workerB);
    }
}
