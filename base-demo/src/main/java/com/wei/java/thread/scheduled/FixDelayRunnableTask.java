package com.wei.java.thread.scheduled;

import com.wei.java.util.SystemOutUtil;

public class FixDelayRunnableTask implements Runnable{
    private int i = 0;

    @Override
    public void run() {
        ++i;
        SystemOutUtil.println("FixDelayRunnableTask start " + i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("FixDelayRunnableTask end " + i);
    }
}
