package com.wei.java.thread.stop.interrupt;

/**
 * isInterrupted读取对方线程对象的中断标志位，只是读取，不会重置
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class InterruptTest4 {
    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("stop 1??" + thread.isInterrupted());
        System.out.println("stop 2??" + thread.isInterrupted());
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 500000; i++) {
                i++;
//            System.out.println("i="+(i+1));
            }
        }
    }
}
