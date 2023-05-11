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
            System.out.println("main 1 main状态：" + MyThread.interrupted()); // 错误，这是Thread.interrupted() 判断当前线程是否被 中断
            System.out.println("main 2 状态：" + myThread.isInterrupted()); // 判断myThread是否被中断
            //开始打断
            myThread.interrupt();
            //此时线程还没中断
            System.out.println("main 3 状态：" + myThread.isInterrupted()); // 判断myThread是否被中断
            Thread.sleep(1000);
            //此时线程已经判断中断
            System.out.println("main 4 状态：" + myThread.isInterrupted()); // 判断myThread是否被中断
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
                System.out.println("run 已经被打断interrupted，在沉睡中被停止！进入catch，中断标志被重置，中断标志状态：" + this.isInterrupted());
                e.printStackTrace();
            }

        }
    }
}
