package com.wei.java.algorithm.dynamicprog;

import java.util.Scanner;

/**
 * HJ24 合唱队
 * <p>
 * 描述
 * N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
 * T1<T2<T3<...<Ti>....>Tk
 * ，则称这
 * <p>
 * K名同学排成了合唱队形。
 * 通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 * <p>
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * <p>
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 * <p>
 * 数据范围：
 * 1 ≤ n ≤ 3000
 * <p>
 * 1≤n≤3000
 * <p>
 * 输入描述：
 * 用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
 * <p>
 * 输出描述：
 * 最少需要几位同学出列
 * <p>
 * 示例1
 * 输入：
 * 8
 * 186 186 150 200 160 130 197 200
 * 复制
 * 输出：
 * 4
 * 复制
 * 说明：
 * 由于不允许改变队列元素的先后顺序，所以最终剩下的队列应该为186 200 160 130或150 200 160 130
 * <p>
 * <p>
 * 求元素的左边和右边的最长有序字串，则相反得到最少出列多少人
 */
public class PhotoLine {
    public static void main(String[] args) {
        // method1();

    }

    public static void method1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] hs = new int[n];
        for (int i = 0; i < n; ++i) {
            hs[i] = in.nextInt();
        }
        int maxLineNum = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (hs[i] > hs[i - 1] && hs[i] > hs[i + 1]) {
                int increaseOrderMaxNum = getIncreaseOrderMaxNum(hs, i);
                int decreaseOrderMaxNum = getDecreaseOrderMaxNum(hs, i);
                int currentMaxLineNum = increaseOrderMaxNum + decreaseOrderMaxNum + 1;
                if (currentMaxLineNum > maxLineNum) {
                    maxLineNum = currentMaxLineNum;
                }
            }
        }
        System.out.println(n - maxLineNum);
    }

    public static int getIncreaseOrderMaxNum(int[] hs, int midIndex) {
        int maxH = hs[midIndex];
        int[] statusH = new int[maxH];
        for (int i = 0; i < maxH; ++i) {
            statusH[i] = -1;
        }
        for (int i = 0; i < midIndex; ++i) {
            int currentH = hs[i];
            if (currentH < maxH) {
                for (int j = 0; j < currentH; ++j) {
                    if (statusH[j] > -1) {
                        int currentLineNum = statusH[j] + 1;
                        if (currentLineNum > statusH[currentH]) {
                            statusH[currentH] = currentLineNum;
                        }
                    }
                }
                if (statusH[currentH] < 0) {
                    statusH[currentH] = 1;
                }
            }
        }
        int maxLineNum = 0;
        for (int i = 0; i < maxH; i++) {
            if (statusH[i] > maxLineNum) {
                maxLineNum = statusH[i];
            }
        }
        return maxLineNum;
    }

    public static Integer getDecreaseOrderMaxNum(int[] hs, int midIndex) {
        int maxH = hs[midIndex];
        int[] statusH = new int[maxH];
        for (int i = 0; i < maxH; ++i) {
            statusH[i] = -1;
        }
        for (int i = hs.length - 1; i > midIndex; i--) {
            int currentH = hs[i];
            if (currentH < maxH) {
                for (int j = 0; j < currentH; j++) {
                    if (statusH[j] > -1) {
                        int currentNum = statusH[j] + 1;
                        if (currentNum > statusH[currentH]) {
                            statusH[currentH] = currentNum;
                        }
                    }
                }
                if (statusH[currentH] < 0) {
                    statusH[currentH] = 1;
                }
            }
        }
        int maxNum = 0;
        for (int i = 0; i < maxH; ++i) {
            if (statusH[i] > maxNum) {
                maxNum = statusH[i];
            }
        }
        return maxNum;
    }


    public static void method2() {
        
    }

}
