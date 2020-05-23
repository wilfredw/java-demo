package com.wei.java.thread.scheduled;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.Callable;

public class DelayCallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        SystemOutUtil.println("DelayCallableTask start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemOutUtil.println("DelayCallableTask end");
        return 0;
    }
}
