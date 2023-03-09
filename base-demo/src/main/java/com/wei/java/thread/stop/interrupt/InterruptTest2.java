package com.wei.java.thread.stop.interrupt;

/**
 * 错误，interrupted只是查看当前自己线程的标识位
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class InterruptTest2 {
    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();

            System.out.println("stop 1??" + thread.interrupted());
            System.out.println("stop 2??" + thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
