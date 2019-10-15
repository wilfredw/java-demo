package com.wei.java.thread.completablefuture;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor testPoolExecutor = new ThreadPoolExecutor(
                2,
                2,
                0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());

        CompletableRunnable completableRunnable = new CompletableRunnable();
        CompletableFuture.runAsync(completableRunnable, testPoolExecutor);

        CompletableSupplier completableSupplier = new CompletableSupplier();
        CompletableFuture.supplyAsync(completableSupplier, testPoolExecutor);
    }
}
