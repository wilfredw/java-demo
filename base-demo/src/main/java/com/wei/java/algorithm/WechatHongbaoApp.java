package com.wei.java.algorithm;

import java.util.Random;
import java.util.Scanner;

public class WechatHongbaoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入红包总金额");
//        String content = scanner.nextLine();
//        //int totalNum = scanner.nextInt();
//        Integer totalNum = Integer.valueOf(content);
//        System.out.println("请输入红包总人数");
//        content = scanner.nextLine();
//        Integer totalPerson = Integer.valueOf(content);
//        //int totalPerson = scanner.nextInt();
//        System.out.println("请输入红包问候语");
//        String message = scanner.nextLine();
        System.out.println("请输入红包总金额");
        int totalNum = scanner.nextInt();
        System.out.println("请输入红包总人数");
        int totalPerson = scanner.nextInt();

        System.out.println("--- " + scanner.nextLine());

        System.out.println("请输入红包问候语");
        String message = scanner.nextLine();
        System.out.println("输入:");
        System.out.println(totalNum);
        System.out.println(totalPerson);
        System.out.println(message);
        System.out.println("红包:");
        RemainHongbao remainHongbao = new RemainHongbao();
        remainHongbao.setRemainMoney(totalNum);
        remainHongbao.setRemainPerson(totalPerson);
        do {
            System.out.println(getRandomMony(remainHongbao));
        } while (remainHongbao.getRemainPerson() >0);

    }

    public static double getRandomMony(RemainHongbao remainHongbao) {
        double minMoney = 0.01;
        double maxMoney = remainHongbao.getRemainMoney() /
                remainHongbao.getRemainPerson() * 2;
        if (remainHongbao.getRemainPerson() == 1) {
            double money = remainHongbao.getRemainMoney();
            remainHongbao.setRemainPerson(0);
            remainHongbao.setRemainMoney(0);
            return money;
        }
        double maxRemainMoney = remainHongbao.getRemainMoney() -
                (remainHongbao.getRemainPerson() - 1) * minMoney;
        if (maxMoney > maxRemainMoney) {
            maxMoney = maxRemainMoney;
        }
        double money  = minMoney + Math.random() * (maxMoney - minMoney);
        money = Math.floor(money * 100) / 100;

        remainHongbao.setRemainMoney(remainHongbao.getRemainMoney() - money);
        remainHongbao.setRemainPerson(remainHongbao.getRemainPerson() - 1);
        return money;
    }

    public static class RemainHongbao {
        private int remainPerson;
        private double remainMoney;

        public int getRemainPerson() {
            return remainPerson;
        }

        public void setRemainPerson(int remainPerson) {
            this.remainPerson = remainPerson;
        }

        public double getRemainMoney() {
            return remainMoney;
        }

        public void setRemainMoney(double remainMoney) {
            this.remainMoney = remainMoney;
        }
    }
}
