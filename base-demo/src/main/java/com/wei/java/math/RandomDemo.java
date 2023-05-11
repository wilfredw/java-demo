package com.wei.java.math;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double value = Math.floor(Math.random() * 10);
            System.out.println(value);

        }
        System.out.println("----");
        for (int i = 0; i < 10; i++) {

            int v = random.nextInt(10);
            System.out.println(v);
        }
        System.out.println("----");
        for (int i = 0; i < 10; i++) {

            System.out.println(new Random().nextInt(10));

        }
    }
}
