package com.wei.java.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureBaseDemo {
    public static void main(String[] args) {
        CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("finish completable future.");
                stringCompletableFuture.complete("hello");
            }
        }).start();
        String ret = null;
        try {
            ret = stringCompletableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ret " + ret);
    }
}
