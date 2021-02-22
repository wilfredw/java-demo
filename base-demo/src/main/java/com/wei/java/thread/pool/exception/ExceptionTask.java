package com.wei.java.thread.pool.exception;

import com.wei.java.util.SystemOutUtil;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/1/12
 */
public class ExceptionTask implements Runnable {
    @Override
    public void run() {
        SystemOutUtil.println("my thread run start: " + this);
        BusinessException e = new BusinessException();
        e.setCode("A00001");
        throw e;
    }
}

