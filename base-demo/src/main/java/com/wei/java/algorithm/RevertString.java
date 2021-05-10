package com.wei.java.algorithm;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RevertString {

    public static void main(String[] args) {
        String string = "runoob";
        String reverse = new StringBuffer(string).reverse().toString();
        System.out.println("字符串反转前:" + string);
        System.out.println("字符串反转后:" + reverse);
        System.out.println("===============");
        String targetStr = "abcdefghi";

        char[] charArray = targetStr.toCharArray();

        RevertData revertData = new RevertData();
        revertData.setCharArray(charArray);
        revertData.setPos(new AtomicInteger(0));


        int threadNum = 4;
        ThreadPoolExecutor taskPoolExecutor = new ThreadPoolExecutor(
                threadNum,
                threadNum,
                0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        ArrayList<Future> arrayList = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; ++i ){
            ReverseTask reverseTask = new ReverseTask();
            reverseTask.setRevertData(revertData);
            Future future = taskPoolExecutor.submit(reverseTask);
            arrayList.add(future);
        }

        try {
            for (Future future : arrayList) {
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("result: " + new String(charArray));
        taskPoolExecutor.shutdown();

    }


    public static class ReverseTask implements Runnable {

        private RevertData revertData;

        public void setRevertData(RevertData revertData) {
            this.revertData = revertData;
        }

        public RevertData getRevertData() {
            return this.revertData;
        }

        @Override
        public void run() {
            char[] charArray = revertData.getCharArray();
            int n = revertData.getCharArray().length / 2;
            int i;
            while ((i = revertData.getPos().incrementAndGet()) <= n) {
                int j = charArray.length - i;
                char tempc = charArray[i - 1];
                charArray[i - 1] = charArray[j];
                charArray[j] = tempc;

            }
        }
    }


    public static class RevertData {
        private char[] charArray;
        private AtomicInteger pos;

        public char[] getCharArray() {
            return charArray;
        }

        public void setCharArray(char[] charArray) {
            this.charArray = charArray;
        }

        public AtomicInteger getPos() {
            return pos;
        }

        public void setPos(AtomicInteger pos) {
            this.pos = pos;
        }
    }

}
