package com.wei.java.thread.waitnotify;

import com.wei.java.util.SystemOutUtil;

class WaitNotifyProducer  implements Runnable{
    private int index;
    private WaitNotifyData data;
    public WaitNotifyProducer(int index, WaitNotifyData data) {
        this.index = index;
        this.data = data;
    }
    private String getName() {
        return "WaitNotifyProducer-" + index;
    }

    @Override
    public void run() {
        SystemOutUtil.println(getName() + " start");
        for (int i = 0; i<10; ++i) {
            SystemOutUtil.println(getName() + " " + i + " run try lock");
            synchronized (this.data) {
                SystemOutUtil.println(getName() + " " + i + " run get lock");
                while (this.data.getDataNum() >= this.data.getMaxNum()) {
                    try {
                        SystemOutUtil.println(getName() + " " + i + " run wait");
                        this.data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int originalData = this.data.getDataNum();
                this.data.setDataNum(originalData+1);
                SystemOutUtil.println(getName() + " " + i + " run produce 1. orignal " + originalData + " current: " + this.data.getDataNum());
                this.data.notifyAll();
            }
        }
        SystemOutUtil.println(getName() + " end");
    }
}