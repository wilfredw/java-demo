package com.wei.java.concurrent.datastruct;

import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.*;

/**
 * LinkedBlockingQueue只是保证线程安全，并不是像生产者消费者那样等待
 * 容量超大
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 10, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>()
        );
        final Long currentMS = System.currentTimeMillis();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("start put i " + i);

                    queue.put(new DelayTask(currentMS + i * 1000));

                    SystemOutUtil.println("finish put i" + i);
                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("start get " + i);
                    DelayTask element = null;
                    try {
                        element = queue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SystemOutUtil.println("finish get " + i + " element " + (element.getExecuteTimeMS() - currentMS));
                }
            }
        });

    }

    public static class DelayTask implements Delayed {
        private long executeTimeMS;

        public DelayTask(long executeTimeMS) {
            this.executeTimeMS = executeTimeMS;
        }

        public long getExecuteTimeMS() {
            return executeTimeMS;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return executeTimeMS - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
