package com.wei.java.thread.stop.interrupt;

/**
 * interrupted只是查看当前自己线程的标识位
 * 并且读取就会重置
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class InterruptTest3 {
    public static void main(String args[]) {
        Thread.currentThread().interrupt();
        System.out.println("stop 1??" + Thread.interrupted());
        System.out.println("stop 2??" + Thread.interrupted());

        System.out.println("End");
    }
}
