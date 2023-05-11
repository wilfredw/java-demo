package com.wei.java.algorithm.other;

/**
 * 一个数x个位数是4，将个位数的4移动到首位，得到的数等于x的4倍。
 * 求x的最小值
 */
public class Number4TimesEqual {

    public static void main(String[] args) {
        int ret1 = method1();
        System.out.println("1: " + ret1);
        int ret2 = method2();
        System.out.println("2: " + ret2);
    }

    public static int method1() {
        int x = 0;
        int currentBit = 4;
        int prevPlusBit = 0;
        int currentBitIndex = 1;
        int nextBit = 0;

        do {
            x += currentBit * (int) Math.round(Math.pow(10, currentBitIndex - 1));
            nextBit = (currentBit * 4 + prevPlusBit) % 10;
            prevPlusBit = (currentBit * 4 + prevPlusBit) / 10;
            ++currentBitIndex;
            currentBit = nextBit;
        } while (!(currentBit == 4 && prevPlusBit == 0));

        return x;
    }

    public static int method2() {
        int x = 0;
        for (int k = 1; k < 100; ++k) {
            long tmp = (4 * Math.round(Math.pow(10, k)) - 16) % 39;
            if (tmp == 0) {
                x = (int) (4 * Math.round(Math.pow(10, k)) - 16) / 39;
                x = x * 10 + 4;
                break;
            }
        }
        return x;
    }

}
