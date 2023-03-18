package com.wei.java.algorithm;

import com.wei.java.thread.pool.exception.BusinessException;

/**
 * 默认跑步机实现
 */
public class DefaultRunnerMachine implements RunnerMachine {
    private RunnerMachineStatusEnum status;
    private long startTimeStamp ;
    private long currentSpeedStartTimeStamp ;

    private long endTimestamp ;
    private long distance ;
    private long speed;
    private static final long DEFAULT_INIT_SPEED = 10;


    public DefaultRunnerMachine() {
        status = RunnerMachineStatusEnum.STOP;
        startTimeStamp = 0L;
        endTimestamp = 0L;
        distance = 0L;
        speed = 0L;
    }

    @Override
    public void start() throws BusinessException {
        if (status == RunnerMachineStatusEnum.START) {
            return ;
        }
        doStart();
    }

    private void doStart() {
        status = RunnerMachineStatusEnum.START;
        startTimeStamp = System.currentTimeMillis();
        currentSpeedStartTimeStamp = startTimeStamp;
        speed = DEFAULT_INIT_SPEED;
    }

    @Override
    public void end() throws BusinessException {
        long currentTime = doSetSpeed(0L);
        endTimestamp = currentTime;
        status = RunnerMachineStatusEnum.STOP;
    }

    @Override
    public void setSpeed(int speed) throws BusinessException {
        doSetSpeed(speed);
    }

    @Override
    public void changeSpeed(int speed, ChangeSpeedEnum changeSpeedEnum) throws BusinessException {
        long toSpeed = 0L;
        if (changeSpeedEnum == ChangeSpeedEnum.INCREASE) {
            toSpeed = this.speed + speed;
        } else {
            toSpeed = this.speed  - speed;
            if (toSpeed <= 0) {
                return ;
            }
        }
        doSetSpeed(toSpeed);
    }

    private long doSetSpeed(long toSpeed) {
        long currentTimestamp = System.currentTimeMillis();
        distance = distance + speed * (currentTimestamp - currentSpeedStartTimeStamp);
        currentSpeedStartTimeStamp = currentTimestamp;
        this.speed = speed;
        return currentTimestamp;
    }

    @Override
    public RunStats getRunStats() throws BusinessException {
        RunStats runStats = new RunStats();
        if (status == RunnerMachineStatusEnum.STOP) {
            long totalTime = this.endTimestamp - this.startTimeStamp;
            runStats.setTime(totalTime);
            runStats.setCurrentSpeed(this.speed);
            runStats.setAverageSpeed(this.distance / totalTime);
            runStats.setDistance(this.distance);
        } else {
            long currentTimestamp = System.currentTimeMillis();
            long totalTime =currentTimestamp - this.startTimeStamp;
            long totalDistance = this.speed * (currentTimestamp - this.currentSpeedStartTimeStamp) + this.distance;
            runStats.setTime(totalTime);
            runStats.setCurrentSpeed(this.speed);
            runStats.setAverageSpeed(totalDistance / totalTime);
            runStats.setDistance(totalDistance);
        }
        return runStats;
    }
}
