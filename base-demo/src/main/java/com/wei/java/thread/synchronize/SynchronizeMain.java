package com.wei.java.thread.synchronize;

import com.wei.java.util.SystemOutUtil;

public class SynchronizeMain {
    public static void main(String[] args) {
        SynchronizeTask synchronizeTask = new SynchronizeTask();
        Thread t1 = new Thread(synchronizeTask, "t1");
        Thread t2 = new Thread(synchronizeTask, "t2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("Main end " + synchronizeTask.getResult());
    }
}
