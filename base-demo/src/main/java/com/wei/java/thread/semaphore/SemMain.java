package com.wei.java.thread.semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SemMain {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        SemPool semPool = new SemPool(20);

        executor.execute(new SemTask(semPool, 9));
        executor.execute(new SemTask(semPool, 14));
        executor.execute(new SemTask(semPool, 13));
        executor.shutdown();
    }
}
