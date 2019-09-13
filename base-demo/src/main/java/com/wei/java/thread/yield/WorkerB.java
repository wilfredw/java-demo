package com.wei.java.thread.yield;

import com.wei.java.util.SystemOutUtil;

public class WorkerB implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i < 10; ) {
            if (Data.i == i) {
                SystemOutUtil.println(this + "process" + i);
                Data.i ++;
                i = i+2;
            } else {
                SystemOutUtil.println(this + "yield for" + i);
                Thread.yield();
            }
        }
    }
}
