package com.wei.java.thread;

import com.wei.java.util.SystemOutUtil;

/**
 * Created by viruser on 2019/6/3.
 */
public class MyTaskThread extends Thread {
    @Override
    public void run() {
        SystemOutUtil.println("my thread run start: " + this);
        try {
            for(int i = 0; i < 10; ++i) {
                SystemOutUtil.println("do run " + this);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("my thread run end: " + this);
    }
}
