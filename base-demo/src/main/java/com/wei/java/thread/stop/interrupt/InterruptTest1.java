package com.wei.java.thread.stop.interrupt;

/**
 * interrupt 只是标记，并不能停止对方线程
 * 需要对象线程判断（手动或者阻塞io操作里比如sleep等）
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class InterruptTest1 {
    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 500000; i++) {
                System.out.println("i=" + (i + 1));
            }
        }
    }
}
