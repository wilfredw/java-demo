package com.wei.java.datetime.performance.currenttime;

import com.wei.java.util.SystemOutUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2023/1/29
 */
public class SystemCurrentPerformanceDemo {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {

        int cpuNum = Runtime.getRuntime().availableProcessors();
        SystemOutUtil.println("CPU cores: " + cpuNum);

        long runTimeMS = 30 * 1000;
        List<Thread> threadList = new ArrayList<>();

        int threadNum = 100;
        CyclicBarrier workBarrier = new CyclicBarrier(threadNum);
        for (int i = 0; i < threadNum; ++i) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        workBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    long startTimeMS = getCurrentTimeMS();
                    Long timeMSCount = 0L;
                    long lastTimeMS = 0L;
                    long currentTimeMS = 0L;
                    do {
                        currentTimeMS = getCurrentTimeMS();
                        if (currentTimeMS > lastTimeMS) {
                            timeMSCount++;
                            lastTimeMS = currentTimeMS;
                        }
                    } while (currentTimeMS < startTimeMS + runTimeMS);

                    System.out.println(
                            "thread: " + Thread.currentThread().getName() + " start: " + formatter.format(new Date(startTimeMS)) +
                                    " end: " +
                                    formatter.format(new Date(currentTimeMS)) + "size: " + timeMSCount);
                }
            }, "MyThread" + i);
            threadList.add(t);

        }

        long starttime = System.currentTimeMillis();
        for (Thread t : threadList) {
            t.start();
        }

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long costTime = endTime - starttime;
        SystemOutUtil.println("cost: " + costTime);
    }

    public static long getCurrentTimeMS() {
        return System.currentTimeMillis();
    }

}
