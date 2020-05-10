package com.wei.java.thread.synchronize;

import com.wei.java.util.SystemOutUtil;

public class SynchronizeTask implements Runnable {
    private volatile int x;


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            for (int i = 0; i < 10; i++) {
                SystemOutUtil.println(currentThread.getName() + " start process " + i);
                synchronized (this) {
                    SystemOutUtil.println(currentThread.getName() + " get lock and work " + i);
                    x = x + 1;
                    Thread.sleep(1000);

                }
                SystemOutUtil.println(currentThread.getName() + " finish process " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        synchronized (this) {
            return ("SynchronizeTask: " + x);
        }
    }
}
