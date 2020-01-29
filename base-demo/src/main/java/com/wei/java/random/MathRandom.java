package com.wei.java.random;

import com.wei.java.util.SystemOutUtil;

public class MathRandom {
    public static void main(String[] args) {
        // singleton Random instance
        SystemOutUtil.println("" + Math.random()); // [0.0 , 1.0)
        int minNum = 2;
        int maxNum = 100;
        double randomNum = minNum + Math.random() * (maxNum - minNum);
        SystemOutUtil.println("" + randomNum);
    }
}
