package com.wei.java.thread.stop;

/**
 * 异常法退出
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class ExceptionStopDemo {
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(100);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end!");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500000; i++) {
                    if (MyThread.interrupted()) {
                        System.out.println("已经是停止状态了，我要退出了！");
                        throw new InterruptedException();
                    }
                    System.out.println("i = " + (i + 1));
                }

                System.out.println("如果我被输出了，则代表线程没有停止");
            } catch (InterruptedException e) {
                System.out.println("在MyThread类中的run方法中被捕获");
                e.printStackTrace();
            }
        }
    }
}
