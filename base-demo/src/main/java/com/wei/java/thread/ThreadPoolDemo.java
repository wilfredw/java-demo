package com.wei.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by viruser on 2019/6/3.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        Thread t1 = new MyTaskThread();
        Thread t2 = new MyTaskThread();
        Thread t3 = new MyTaskThread();
        System.out.println("start execute task");
        singlePool.execute(t1);
        singlePool.execute(t2);
        singlePool.execute(t3);
        System.out.println("finish execute task");

        ExecutorService fixNumPool = Executors.newFixedThreadPool(4);
        System.out.println("start execute task");
        fixNumPool.execute(t1);
        fixNumPool.execute(t2);
        fixNumPool.execute(t3);
        System.out.println("finish execute task");
        return ;
    }

}
