package com.wei.java.thread.stop;

/**
 * sleep过程中中断停止
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class SleepInterruptDemo1 {
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(2000);
            System.out.println("状态：" + MyThread.interrupted());
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("run-----------start");
                Thread.sleep(5000);
                System.out.println("run-----------end");
            } catch (InterruptedException e) {
                System.out.println("在沉睡中被停止！进入catch，线程的是否处于停止状态：" + this.isInterrupted());
                e.printStackTrace();
            }

        }
    }
}
