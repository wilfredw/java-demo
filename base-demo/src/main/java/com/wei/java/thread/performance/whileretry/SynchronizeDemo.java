package com.wei.java.thread.performance.whileretry;

import com.wei.java.util.SystemOutUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2023/1/29
 */
public class SynchronizeDemo {
    public static volatile Integer num;
    public static ReentrantLock updateLock = new ReentrantLock();
    public static final Integer target = 100000000;

    public static void main(String[] args) {
        num = 0;


        int cpuNum = Runtime.getRuntime().availableProcessors();
        SystemOutUtil.println("CPU cores: " + cpuNum);

        List<Thread> threadList = new ArrayList<>();

        int threadNum = 500;
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
                    while (num.compareTo(target) < 0) {
                        synchronized (updateLock) {
                            Integer current = num;
                            if (current.compareTo(target) < 0) {
                                Integer target = current + 1;
                                num = target;
                            }
                        }
                    }
                }
            });
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
        SystemOutUtil.println("cost: " + costTime + " ret: " + num);
    }
}
