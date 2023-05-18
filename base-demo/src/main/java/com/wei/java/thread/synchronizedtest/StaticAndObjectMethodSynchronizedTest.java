package com.wei.java.thread.synchronizedtest;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类的实例的方法的synchronized是锁的类的实例对象
 */
public class StaticAndObjectMethodSynchronizedTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        Test1 object1 = new Test1("===object1===");

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.objectTest();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.objectTest();
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
                Test1.staticTest();
            }
        });

        try {
            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SystemOutUtil.println("end");
    }

    public static class Test1 {
        private String name;

        public Test1(String name) {
            this.name = name;
        }

        public static synchronized void staticTest() {
            SystemOutUtil.println("Test1 staticTest start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 staticTest end");
        }

        public synchronized void objectTest() {
            SystemOutUtil.println("Test1 " + name + " objectTest start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 " + name + " objectTest end");
        }

    }

}
