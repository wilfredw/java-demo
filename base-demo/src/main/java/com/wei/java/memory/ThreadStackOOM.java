package com.wei.java.memory;

/**
 * 通过不断创建线程导致OOM
 * 会导致系统假死
 *
 * VM Args: -Xss2M
 */
public class ThreadStackOOM {
    private void dontStop() {
        while(true) {

        }
    }

    public void run() {
        while(true) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            t.start();
        }
    }

    public static void main(String[] args) {
        ThreadStackOOM threadStackOOM = new ThreadStackOOM();
        threadStackOOM.run();
    }
}
