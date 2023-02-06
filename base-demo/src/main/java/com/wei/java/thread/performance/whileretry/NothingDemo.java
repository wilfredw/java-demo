package com.wei.java.thread.performance.whileretry;

import com.wei.java.util.SystemOutUtil;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2023/1/29
 */
public class NothingDemo {
    public static volatile Integer num;
    public static final Integer target = 100000000;

    public static void main(String[] args) {
        num = 0;


        int cpuNum = Runtime.getRuntime().availableProcessors();
        SystemOutUtil.println("CPU cores: " + cpuNum);

        long starttime = System.currentTimeMillis();

        while (num.compareTo(target) < 0) {
            Integer current = num;
            if (current.compareTo(target) < 0) {
                Integer target = current + 1;
                num = target;
            }
        }

        long endTime = System.currentTimeMillis();
        long costTime = endTime - starttime;
        SystemOutUtil.println("cost: " + costTime + " ret: " + num);
    }
}
