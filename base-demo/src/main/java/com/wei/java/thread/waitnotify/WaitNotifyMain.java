package com.wei.java.thread.waitnotify;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.wei.java.util.SystemOutUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;

class WaitNotifyMain {
    public static void main(String[] args) {
        WaitNotifyData waitNotifyData = new WaitNotifyData(0, 10);
        ExecutorService executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 4,
                Runtime.getRuntime().availableProcessors() * 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ArrayList<Runnable> consumerList = new ArrayList<>(4);
        ArrayList<Runnable> producerList = new ArrayList<>(4);
        LinkedList<Future> futureLinkedList = new LinkedList<>();
        for (int i = 1; i <= 4; ++i) {
            WaitNotifyConsumer c = new WaitNotifyConsumer(i, waitNotifyData);
            WaitNotifyProducer p = new WaitNotifyProducer(i, waitNotifyData);
            Future futureC = executorService.submit(c);
            Future futureP = executorService.submit(p);
            consumerList.add(c);
            producerList.add(p);
            futureLinkedList.add(futureC);
            futureLinkedList.add(futureP);
        }

        for (Future f : futureLinkedList) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        SystemOutUtil.println("Main over!");
        return ;
    }
}