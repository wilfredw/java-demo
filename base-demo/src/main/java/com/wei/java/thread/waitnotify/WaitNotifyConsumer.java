package com.wei.java.thread.waitnotify;

import com.wei.java.util.SystemOutUtil;

class WaitNotifyConsumer implements Runnable {
    private int index;
    private WaitNotifyData data;

    public WaitNotifyConsumer(int index, WaitNotifyData data) {
        this.index = index;
        this.data = data;
    }

    @Override
    public void run() {
        SystemOutUtil.println(getName() + " start");
        for (int i = 0; i < 10; ++i) {
            SystemOutUtil.println(getName() + " " + i + " run try lock");
            synchronized (data) {
                SystemOutUtil.println(getName() + " " + i + " run get lock");
                while (data.getDataNum() <= 0) {
                    SystemOutUtil.println(getName() + " " + i + " run wait");
                    try {
                        data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int originalData = data.getDataNum();
                data.setDataNum(originalData-1);
                SystemOutUtil.println(getName() + " " + i + " run consume 1. original: " + originalData + " current: " + data.getDataNum());
                this.data.notifyAll();
            }
        }
        SystemOutUtil.println(getName() + " end");
    }

    private String getName() {
        return "WaitNotifyConsumer-" + index;
    }
}