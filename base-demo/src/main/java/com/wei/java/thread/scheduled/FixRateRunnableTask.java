package com.wei.java.thread.scheduled;

import com.wei.java.util.SystemOutUtil;

public class FixRateRunnableTask implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        SystemOutUtil.println("FixRateRunnableTask start " + ++i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("FixRateRunnableTask end " + i);
    }
}
