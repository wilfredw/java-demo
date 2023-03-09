package com.wei.java.thread.stop;

/**
 * stop()方法以及作废，因为如果强制让线程停止有可能使一些清理性的工作得不到完成。另外一个情况就是对锁定的对象进行了解锁，导致数据得不到同步的处理，出现数据不一致的问题。
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class ThreadStopDemo {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(2000);
        try {
            thread.stop();
        } catch (ThreadDeath e) {
            System.out.println("进入异常catch");
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        private int i = 0;

        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    System.out.println("i=" + i);
                    i++;
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
