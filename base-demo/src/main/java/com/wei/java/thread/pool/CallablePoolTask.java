package com.wei.java.thread.pool;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.Callable;

public class CallablePoolTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        SystemOutUtil.println("CallablePoolTask call...");
        Thread.sleep(5000);
        return "call result";
    }
}
