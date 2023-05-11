package com.wei.java.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 蓄水池抽样算法
 */
public class ReservoirSampling {
    public static void main(String[] args) {
        int[] dataStream = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < 10; i++) {
            System.out.println(JSON.toJSONString(process(dataStream, 2)));
        }
    }

    public static int[] process(int[] dataStream, int reservoirNum) {
        int[] reservoirNums = new int[reservoirNum];

        for (int i = 0; i < dataStream.length; ++i) {
            if (i < reservoirNum) {
                reservoirNums[i] = dataStream[i];
            } else {
                int randomNum = (int) Math.floor(Math.random() * (i + 1));
                if (randomNum < reservoirNum) {
                    reservoirNums[randomNum] = dataStream[i];
                }
            }
        }
        return reservoirNums;
    }
}
