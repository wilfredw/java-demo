package com.wei.java.thread.pool;

import com.wei.java.thread.pool.MyThreadPoolTask;
import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by viruser on 2019/6/3.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        Runnable t1 = new MyThreadPoolTask();
        Runnable t2 = new MyThreadPoolTask();
        Runnable t3 = new MyThreadPoolTask();
        CallablePoolTask callablePoolTask = new CallablePoolTask();
        SystemOutUtil.println("start execute task");
        singlePool.execute(t1);
        singlePool.execute(t2);
        singlePool.execute(t3);
        Future<String> future = singlePool.submit(callablePoolTask);
        SystemOutUtil.println("finish execute task");
        try {
            SystemOutUtil.println("future get result: " + future.get() + " at " + future);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService fixNumPool = Executors.newFixedThreadPool(4);
        SystemOutUtil.println("start execute task");
        fixNumPool.execute(t1);
        fixNumPool.execute(t2);
        fixNumPool.execute(t3);
        SystemOutUtil.println("finish execute task");

        return ;
    }

}
