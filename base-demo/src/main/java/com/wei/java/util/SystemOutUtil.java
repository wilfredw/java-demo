package com.wei.java.util;

/**
 * Created by viruser on 2019/9/7.
 */
public class SystemOutUtil {
    public static void println(String msg) {
        System.out.println(DateTimeUtil.nowSimpleFormat() + " tid:" + Thread.currentThread().getId() + " " + msg);
    }

    public static void print(String msg) {
        System.out.print(DateTimeUtil.nowSimpleFormat() + " tid:" + Thread.currentThread().getId() + " " + msg);
    }
}
