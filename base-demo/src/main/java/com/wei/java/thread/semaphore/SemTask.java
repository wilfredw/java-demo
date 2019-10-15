package com.wei.java.thread.semaphore;

import com.wei.java.util.SystemOutUtil;

public class SemTask implements Runnable {
    private SemPool semPool;
    private int x;
    public SemTask(SemPool semPool, int x) {
        this.semPool = semPool;
        this.x = x;
    }

    @Override
    public void run() {
        boolean acquireSuccess = false;
        SystemOutUtil.println("start acquire " + x);
        try {
            semPool.getSemaphore().acquire(x);
            acquireSuccess = true;
            SystemOutUtil.println("success acquire " + x);
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
            SystemOutUtil.println("interrupted exception acquire " + x);
        } finally {
            if (acquireSuccess) {
                semPool.getSemaphore().release(x);
                SystemOutUtil.println("success release " + x);
            }
        }
        SystemOutUtil.println("end acquire " + x);
    }
}
