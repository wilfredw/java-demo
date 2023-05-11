package com.wei.java.thread.synchronizedtest;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类静态方法的synchronized是锁的类对象
 */
public class StatickMethodSynchronizedTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Test1.staticTest();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Test1.staticTest();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Test1.staticTest2();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Test1.staticTest2();
            }
        });
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Test2.staticTest();
//            }
//        });
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Test2.staticTest();
//            }
//        });
        try {
            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SystemOutUtil.println("end");
    }

    public static class Test1 {
        public static synchronized void staticTest() {
            SystemOutUtil.println("Test1 staticTest1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 staticTest1 end");
        }

        public static synchronized void staticTest2() {
            SystemOutUtil.println("Test1 staticTest2 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 staticTest2 end");
        }

    }

    public static class Test2 {
        public static synchronized void staticTest() {
            SystemOutUtil.println("Test2 staticTest start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test2 staticTest end");
        }
    }
}
