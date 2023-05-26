package com.wei.java.algorithm.dynamicprog;

/**
 * 动态规划，要根据目标结果值和过程中影响决策的因素来生成动态规划的状态值。
 * <p>
 * 掷骰子模拟
 * <p>
 * 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
 * <p>
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
 * <p>
 * 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
 * <p>
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dice-roll-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：n = 2, rollMax = [1,1,2,2,2,3]
 * 输出：34
 * 解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/dice-roll-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：n = 2, rollMax = [1,1,1,1,1,1]
 * 输出：30
 * <p>
 * 输入：n = 3, rollMax = [1,1,1,2,2,3]
 * 输出：181
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 *
 * 这里每一步骤是，当前筛子投6种情况
 * 中间过程，是上一个投出的数字，以及连续次数，会限制，影响这步骤能投哪些情况硬币加不加没有限制
 * 状态值是每种情况的情况数目，是相加得到最后结果
 */
public class DynamicDice {
    public static int modeNum = 1000000000 + 7;
    public static int diceNum = 6;

    public static void main(String[] args) {
        int n = 2;
        int[] rollMax = new int[] {1, 1, 2, 2, 2, 3};
        int ret = dieSimulator(n, rollMax);
        System.out.println(ret);

        n = 3;
        rollMax = new int[] {1, 1, 1, 2, 2, 3};
        ret = dieSimulator(n, rollMax);

        System.out.println(ret);
    }

    public static int dieSimulator(int n, int[] rollMax) {
        int maxRollMax = 0;
        for (int i : rollMax) {
            if (i > maxRollMax) {
                maxRollMax = i;
            }
        }
        int[][] prevStepStatus = new int[diceNum][maxRollMax];
        int[][] currentStepStatus = new int[diceNum][maxRollMax];
        int[][] temp = null;
        init(prevStepStatus, diceNum, maxRollMax);
        init(currentStepStatus, diceNum, maxRollMax);

        //初始化第一步
        for (int i = 0; i < diceNum; i++) {
            currentStepStatus[i][0] = 1;
        }

        for (int stepIndex = 2; stepIndex <= n; ++stepIndex) {
            temp = prevStepStatus;
            prevStepStatus = currentStepStatus;
            currentStepStatus = temp;
            init(currentStepStatus, diceNum, maxRollMax);

            for (int i = 0; i < diceNum; ++i) {
                for (int j = 0; j < rollMax[i]; ++j) {
                    if (prevStepStatus[i][j] > 0) {
                        for (int k = 0; k < diceNum; ++k) {
                            if (k != i) {
                                currentStepStatus[k][0] = (currentStepStatus[k][0] + prevStepStatus[i][j]) % modeNum;
                            } else {
                                if (j < (rollMax[i] - 1)) {
                                    currentStepStatus[k][j + 1] = (currentStepStatus[k][j + 1] + prevStepStatus[i][j]) % modeNum;
                                }
                            }
                        }
                    }
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < diceNum; ++i) {
            for (int j = 0; j < rollMax[i]; ++j) {
                if (currentStepStatus[i][j] > 0) {
                    ret = (ret + currentStepStatus[i][j]) % modeNum;
                }
            }
        }
        return ret;
    }

    public static void init(int[][] stepStatus, int n, int maxRollMax) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < maxRollMax; ++j) {
                stepStatus[i][j] = 0;
            }
        }
    }
}
