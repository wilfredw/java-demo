package com.wei.java.util;

/**
 * Created by viruser on 2019/9/7.
 */
public class SystemOutUtil {
    public static void println(String msg) {
        System.out.println(DateTimeUtil.nowSimpleFormat() + " " + msg);
    }
}
