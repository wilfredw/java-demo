package com.wei.java.thread.scheduled;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsApp {
    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        SystemOutUtil.println("start schedule");
        scheduledExecutorService.schedule(new DelayCallableTask(), 1000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new FixRateRunnableTask(), 1000, 1000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new FixDelayRunnableTask(), 1000, 1000, TimeUnit.MILLISECONDS);
        SystemOutUtil.println("end schedule");
        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
        return ;
    }
}
