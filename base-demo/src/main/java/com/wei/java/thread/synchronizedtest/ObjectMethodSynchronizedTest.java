package com.wei.java.thread.synchronizedtest;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类的实例的方法的synchronized是锁的类的实例对象
 */
public class ObjectMethodSynchronizedTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        Test1 object1 = new Test1("===object1===");
        Test1 object2 = new Test1("<<<object2>>>");


        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.test();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.test();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.test2();
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                object1.test2();
            }
        });
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                object2.test();
//            }
//        });
//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                object2.test();
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
        private String name;

        public Test1(String name) {
            this.name = name;
        }

        public synchronized void test() {
            SystemOutUtil.println("Test1 " + name + " Test1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 " + name + " Test1 end");
        }

        public synchronized void test2() {
            SystemOutUtil.println("Test1 " + name + " test2 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SystemOutUtil.println("Test1 " + name + " test2 end");
        }

    }

}
