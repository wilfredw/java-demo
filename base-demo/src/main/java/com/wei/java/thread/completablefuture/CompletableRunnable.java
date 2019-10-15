package com.wei.java.thread.completablefuture;

import com.wei.java.util.SystemOutUtil;

public class CompletableRunnable implements Runnable{
    @Override
    public void run() {
        SystemOutUtil.println("CompletableRunnable start");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("CompletableRunnable end");
    }
}
