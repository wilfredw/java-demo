package com.wei.java.basic;

import com.wei.java.util.SystemOutUtil;

import java.util.Objects;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/1/19
 */
public class EqualDemo {
    public static void main(String[] args) {
        int a = 1;
        Integer b = 1;
        boolean result = Objects.equals(a, b);
        SystemOutUtil.println("int equal with Integer: " + result);

        result = Objects.equals(null, b);
        SystemOutUtil.println("null equal with Integer: " + result);

        long costTimeMS = 738;
        long costTimeHMS = costTimeMS / 100;
        System.out.println("cost time: " + costTimeMS + " ms, " + costTimeHMS + " hms.");
    }
}
