package com.wei.java.thread.future;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.*;

/**
 * Created by viruser on 2020/3/4.
 */
public class FutureDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());

        FutureCallableTask task1 = new FutureCallableTask(1);
        Future<String> future1 = executor.submit(task1);
        try {
            String result = future1.get(6, TimeUnit.SECONDS);
            SystemOutUtil.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FutureCallableTask task2 = new FutureCallableTask(2);
        Future<String> future2 = executor.submit(task2);
        try {
            String result = future2.get(3, TimeUnit.SECONDS);
            SystemOutUtil.println(result);
        } catch (Exception e) {
            boolean iscancel = future2.cancel(true);
            SystemOutUtil.println("iscancel " + iscancel);
            e.printStackTrace();
        }
    }
}
