package com.wei.java.algorithm;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class GetBigNumApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数："); // 14235  54321
        Integer content = null;
        try {
            content = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("请输入整数!!!");
            e.printStackTrace();
            return ;
        }
        Integer nextInt = content;
        Integer currentNum = null;
        Integer biggerNum = null;
        Integer biggerInt = null;
        TreeSet<Integer> numSet = new TreeSet<>();
        while (nextInt > 0) {
            currentNum = nextInt % 10;
            nextInt = nextInt / 10;
            numSet.add(currentNum);
            if ((biggerNum = numSet.ceiling(currentNum + 1)) != null) {
                numSet.remove(biggerNum);
                biggerInt = nextInt;
                biggerInt = biggerInt * 10 + biggerNum;
                Iterator<Integer> it = numSet.iterator();
                while (it.hasNext()) {
                    Integer value = it.next();
                    biggerInt = biggerInt * 10 + value;
                }
                break;
            }
        }
        if (biggerInt == null) {
            System.out.println("No Bigger Number!");
        } else {
            System.out.println("Bigger Number: " + biggerInt);
        }
    }
}
