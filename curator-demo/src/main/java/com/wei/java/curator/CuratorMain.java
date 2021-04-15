package com.wei.java.curator;

import com.wei.java.curator.consumer.ConsumerRunner;
import com.wei.java.curator.provider.ProviderRunner;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/4/15
 */
public class CuratorMain {
    public static void main(String[] args) {
        try {
            Thread consumerThread = new Thread(
                    new ConsumerRunner(), "consumerThread");
            consumerThread.start();
            Thread providerThread = new Thread(
                    new ProviderRunner(), "providerThread");
            providerThread.start();
            Thread.sleep(1000 * 60 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
