package com.wei.java.algorithm.dynamicprog;

/**
 * 0-1 背包问题
 * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class ZeroOneBagProblemWeight {

    private static int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private static int n = 5; // 物品个数
    private static int w = 9; // 背包承受的最大重量

    /**
     * 回溯的解决方法，也就是穷举搜索所有可能的装法，然后找出满足条件的最大值
     * 动态总结缓存相同中间执行状态节点
     * 二叉树缓存遍历算法
     *
     * @param i
     * @param cw
     */
    private static boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false
    private static int maxW = Integer.MIN_VALUE; // 结果放到maxW中

    public static void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        if (mem[i][cw]) {
            return; // 重复状态
        }
        mem[i][cw] = true; // 记录(i, cw)这个状态
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

    public static void main(String[] args) {
        f(0, 0);
        System.out.println(maxW);

        int ret = knapsack(weight, n, w);
        System.out.println(ret);

        ret = knapsack2(weight, n, w);
        System.out.println(ret);
    }

    public static int knapsack(int[] nodeWeight, int nodeTotalNum, int bagWeight) {
        boolean[][] nodeCalcStates = new boolean[nodeTotalNum][bagWeight + 1];

        // init first node
        int nodeIndex = 0;
        int includeNodeW = 0;
        nodeCalcStates[nodeIndex][includeNodeW] = true;
        if (nodeWeight[nodeIndex] <= bagWeight) {
            includeNodeW = nodeWeight[nodeIndex];
            nodeCalcStates[nodeIndex][includeNodeW] = true;
        }

        for (nodeIndex = 1; nodeIndex < nodeTotalNum; nodeIndex++) {
            int lastNodeI = nodeIndex - 1;
            // 不放的情况
            for (int j = 0; j <= bagWeight; ++j) {
                if (nodeCalcStates[lastNodeI][j] == true) { // 没必要判断？默认false
                    nodeCalcStates[nodeIndex][j] = nodeCalcStates[lastNodeI][j];
                }
            }
            // 当前物品放进去的情况
            for (int j = 0; j + nodeWeight[nodeIndex] <= bagWeight; ++j) {
                if (nodeCalcStates[lastNodeI][j] == true) {
                    includeNodeW = j + nodeWeight[nodeIndex];
                    nodeCalcStates[nodeIndex][includeNodeW] = true;
                }
            }
        }

        nodeIndex = nodeTotalNum - 1;
        for (int j = bagWeight; j >= 0; --j) {
            if (nodeCalcStates[nodeIndex][j] == true) {
                return j;
            }
        }

        return 0;
    }


    public static int knapsack2(int[] nodeWeight, int nodeTotalNum, int bagWeight) {
        boolean[] nodeCalcStates = new boolean[bagWeight + 1];

        // init first node
        int nodeIndex = 0;
        int includeNodeW = 0;
        nodeCalcStates[includeNodeW] = true;
        if (nodeWeight[nodeIndex] <= bagWeight) {
            includeNodeW = nodeWeight[nodeIndex];
            nodeCalcStates[includeNodeW] = true;
        }

        for (nodeIndex = 1; nodeIndex < nodeTotalNum; nodeIndex++) {
            int lastNodeI = nodeIndex - 1;

            if (nodeWeight[nodeIndex] <= bagWeight) {
                // 当前物品放进去的情况
                for (int j = bagWeight - nodeWeight[nodeIndex]; j >= 0; --j) {
                    if (nodeCalcStates[j] == true) {
                        includeNodeW = j + nodeWeight[nodeIndex];
                        nodeCalcStates[includeNodeW] = true;
                    }
                }
            }
        }

        for (int j = bagWeight; j >= 0; --j) {
            if (nodeCalcStates[j] == true) {
                return j;
            }
        }

        return 0;
    }
}
