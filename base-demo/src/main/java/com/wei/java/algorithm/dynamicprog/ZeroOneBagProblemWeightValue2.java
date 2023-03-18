package com.wei.java.algorithm.dynamicprog;

/**
 * 0-1 背包问题
 * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
 *
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class ZeroOneBagProblemWeightValue2 {

    private static int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private static int[] value = {4, 3, 7, 6, 5}; // 物品重量
    private static int n = 5; // 物品个数
    private static int w = 9; // 背包承受的最大重量
    private static int v = 17; // 背包承受的最大重量

    /**
     * 回溯的解决方法，也就是穷举搜索所有可能的装法，然后找出满足条件的最大值
     * 动态总结缓存相同中间执行状态节点
     * 二叉树缓存遍历算法
     *
     * @param i
     * @param cw
     */
    private static boolean[][][] mem = new boolean[5][10][100]; // 备忘录，默认值false
    private static int maxV = 0; // 结果放到maxW中

    public static void f(int i, int cw, int cv) { // 调用f(0, 0)
        if (i == n
                || (cw == w && cv == v)) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        if (mem[i][cw][cv]) {
            return; // 重复状态
        }
        mem[i][cw][cv] = true; // 记录(i, cw)这个状态
        f(i + 1, cw, cv); // 选择不装第i个物品
        if ((cw + weight[i] <= w) && (cv + value[i] <= v)) {
            f(i + 1, cw + weight[i], cv + value[i]); // 选择装第i个物品
        }
    }

    public static void main(String[] args) {
        f(0, 0, 0);
        System.out.println(maxV);

        int ret = knapsack2(weight, value, n, w, v);
        System.out.println(ret);
    }

    public static int knapsack2(int[] nodeWeight, int[] nodeValue, int nodeTotalNum, int bagWeight, int bagValue) {
        boolean[][] nodeCalcStates = new boolean[bagWeight + 1][bagValue + 1];

        // init first node
        int nodeIndex = 0;
        int includeNodeW = 0;
        int includeNodeV = 0;
        nodeCalcStates[includeNodeW][includeNodeV] = true;
        if (nodeWeight[nodeIndex] <= bagWeight
                && nodeValue[nodeIndex] <= bagValue) {
            includeNodeW = nodeWeight[nodeIndex];
            includeNodeV = nodeValue[nodeIndex];
            nodeCalcStates[includeNodeW][includeNodeV] = true;
        }

        for (nodeIndex = 1; nodeIndex < nodeTotalNum; nodeIndex++) {

            if (nodeWeight[nodeIndex] <= bagWeight
                    && nodeValue[nodeIndex] <= bagValue) {
                // 当前物品放进去的情况
                for (int j = bagWeight - nodeWeight[nodeIndex]; j >= 0; --j) {
                    for (int k = bagValue - nodeValue[nodeIndex]; k >= 0; --k) {
                        if (nodeCalcStates[j][k] == true) {
                            includeNodeW = j + nodeWeight[nodeIndex];
                            includeNodeV = k + nodeValue[nodeIndex];
                            nodeCalcStates[includeNodeW][includeNodeV] = true;
                        }
                    }
                }
            }
        }

        for (int k = bagValue; k >= 0; --k) {
            for (int j = bagWeight; j >= 0; --j) {
                if (nodeCalcStates[j][k] == true) {
                    return k;
                }
            }
        }

        return 0;
    }
}
