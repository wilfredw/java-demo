package com.wei.java.thread.waitnotify;

class WaitNotifyConsumer implements Runnable {
    private WaitNotifyData data;

    public WaitNotifyConsumer(WaitNotifyData data) {
        this.data = data;
    }

    @Override
    public void run() {
        synchronized (data) {
            ;
        }
    }
}