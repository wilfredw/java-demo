package com.wei.java.thread;

import com.wei.java.util.DateTimeUtil;
import com.wei.java.util.SystemOutUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wei on 2019/7/1.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        SystemOutUtil.println("main start");
//        MyTaskThread myTaskThread = new MyTaskThread();
        Thread myTaskThread = new Thread(new MyTaskRunnable(), "myTaskThread");
        myTaskThread.start();
        Thread runThread = new Thread(new MyTaskRunnable(), "runThread");
        runThread.start();
        SystemOutUtil.println("thread created");
        try {
            myTaskThread.join();
            runThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            SystemOutUtil.println("when join, interrupted");
        }
        SystemOutUtil.println("main over");
    }
}
