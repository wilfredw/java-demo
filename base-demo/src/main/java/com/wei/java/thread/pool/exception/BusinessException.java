package com.wei.java.thread.pool.exception;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/1/12
 */
public class BusinessException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
