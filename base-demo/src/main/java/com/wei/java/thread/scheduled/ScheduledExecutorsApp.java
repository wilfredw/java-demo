package com.wei.java.thread.scheduled;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsApp {
    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        SystemOutUtil.println("start schedule");
        scheduledThreadPoolExecutor.schedule(new DelayCallableTask(), 1000, TimeUnit.MILLISECONDS);
        RunnableScheduledFuture<?> rateFuture =
                (RunnableScheduledFuture<?>) scheduledThreadPoolExecutor
                        .scheduleAtFixedRate(new FixRateRunnableTask(), 1000, 1000, TimeUnit.MILLISECONDS);
        ScheduledFuture delayFuture =
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(new FixDelayRunnableTask(), 1000, 1000, TimeUnit.MILLISECONDS);
        SystemOutUtil.println("end schedule");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start stop");

//        scheduledThreadPoolExecutor.remove(rateFuture); // 无效
        rateFuture.cancel(false);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledThreadPoolExecutor.shutdown();
        return;
    }
}
