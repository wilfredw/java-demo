package com.wei.java.thread;

/**
 * Created by viruser on 2019/6/3.
 */
public class MyThreadPoolTask implements Runnable {
    @Override
    public void run() {
        System.out.println("my thread run start: " + this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("my thread run end: " + this);
    }
}
