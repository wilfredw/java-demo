package com.wei.java.algorithm.testcase.runnermachine;

/**
 * 跑步状态统计数据
 */
public class RunStats {
    /**
     * 跑步总时长
     */
    private long time;
    /**
     * 跑步总距离
     */
    private long distance;
    /**
     * 跑步平均速度
     */
    private long averageSpeed;
    /**
     * 跑步当前速度
     */
    private long currentSpeed;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(long averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public long getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(long currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
