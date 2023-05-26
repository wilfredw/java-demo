package com.wei.java.concurrent.future;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                SystemOutUtil.println("runAsync start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SystemOutUtil.println("run something");
                SystemOutUtil.println("runAsync over");
            }
        });

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            SystemOutUtil.println("start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String ret = "hello by tid " + Thread.currentThread().getId();
            SystemOutUtil.println("do something and return " + ret);
            return ret;
        });
        CompletableFuture<String> thenApplySCF = stringCompletableFuture.thenApply(t -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String ret = t + " and then apply" + Thread.currentThread().getId();
            SystemOutUtil.println("stringCompletableFuture then apply and return " + ret);
            return ret;
        });
        CompletableFuture<String> thenApplyAsyncSCF = stringCompletableFuture.thenApplyAsync(t -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String ret = t + " and then async apply" + Thread.currentThread().getId();
            SystemOutUtil.println("stringCompletableFuture then async apply and ret " + ret);
            return ret;
        });
        CompletableFuture<String> combineSCF = thenApplySCF.thenCombine(thenApplyAsyncSCF, (a, b) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String ret = "" + a == null ? "" : a + " " + b == null ? "" : b + " and then combine" + Thread.currentThread().getId();
            SystemOutUtil.println("stringCompletableFuture then combine and ret " + ret);
            return ret;
        });
        try {
            SystemOutUtil.println("get result " + stringCompletableFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Object> anyCF = CompletableFuture.anyOf(voidCompletableFuture, combineSCF);
        anyCF.thenApply((a) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String ret = a.toString();
            SystemOutUtil.println("anyOf and ret " + ret);
            return ret;
        });
        try {
            SystemOutUtil.println("any ret " + anyCF.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        SystemOutUtil.println("end");


        CompletableFuture<Void> allCF = CompletableFuture.allOf(voidCompletableFuture, combineSCF);
        CompletableFuture<String> allThenApplyCF = allCF.thenApply(new Function<Void, String>() {
            @Override
            public String apply(Void unused) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (unused == null) {
                    SystemOutUtil.println("allThenApplyCF unused null");
                } else {
                    SystemOutUtil.println("allThenApplyCF unused " + unused.toString());
                }
                String ret = "";
                SystemOutUtil.println("allOf and ret " + ret);
                return ret;
            }
        });
        try {
            SystemOutUtil.println("allThenApplyCF ret " + allThenApplyCF.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        SystemOutUtil.println("end");
    }
}
