package com.wei.java.thread.future;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.Callable;

/**
 * Created by viruser on 2020/3/4.
 */
public class FutureCallableTask implements Callable<String> {
    private Integer id;

    public FutureCallableTask(Integer id) {
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        SystemOutUtil.println("FutureCallableTask call start " + id);
        Thread.sleep(5000);
        SystemOutUtil.println("FutureCallableTask call end " + id);
        return "hello " + id;
    }
}
