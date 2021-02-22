package com.wei.java.thread.pool.exception;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/1/12
 */
public class ExceptionPoolMain {
    public static void main(String[] args) {
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        Future f = singlePool.submit(new ExceptionTask());
        try {
            f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof BusinessException) {
                SystemOutUtil.println("print BusinessException");
                e.getCause().printStackTrace();
                SystemOutUtil.println("print ExecutionException");
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }
        return;
    }

}

