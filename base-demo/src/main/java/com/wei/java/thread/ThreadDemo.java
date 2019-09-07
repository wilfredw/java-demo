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
        MyTaskThread myTaskThread = new MyTaskThread();
        myTaskThread.start();
        Thread runThread = new Thread(new MyTaskRunnable());
        runThread.start();
        SystemOutUtil.println("main over");
    }
}
