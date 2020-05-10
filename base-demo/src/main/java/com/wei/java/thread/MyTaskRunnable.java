package com.wei.java.thread;

import com.wei.java.util.SystemOutUtil;

/**
 * Created by viruser on 2019/9/7.
 */
public class MyTaskRunnable implements Runnable {

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        SystemOutUtil.println("my thread run start: " + current.getName());
        try {
            for(int i = 0; i < 10; ++i) {
                SystemOutUtil.println("do run " + current.getName());
                Thread.sleep(1000);
            }
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("my thread run end: " + current.getName());
    }
}
