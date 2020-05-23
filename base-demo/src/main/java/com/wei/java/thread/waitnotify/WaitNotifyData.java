package com.wei.java.thread.waitnotify;

class WaitNotifyData {
    private int dataNum;
    private int maxNum;

    public WaitNotifyData(int dataNum, int maxNum) {
        this.dataNum = dataNum;
        this.maxNum = maxNum;
    }

    public int getDataNum() {
        return dataNum;
    }

    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
}