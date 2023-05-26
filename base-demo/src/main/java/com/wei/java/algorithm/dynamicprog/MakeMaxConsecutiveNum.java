package com.wei.java.algorithm.dynamicprog;

import java.util.Arrays;

/**
 * 【和MakeZeroMaxConsecutiveNum不一样，这个不需要从0开始，从任意位置开始的最大连续数】
 * 你能构造出连续值的最大数目
 * 给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。第 i 个硬币的值为 coins[i] 。如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
 * <p>
 * 请返回 （包括 0 ），你最多能 构造 出多少个连续整数。
 * <p>
 * 你可能有多个相同值的硬币。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：coins = [1,3]
 * 输出：2
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * 从 0 开始，你可以构造出 2 个连续整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：coins = [1,1,1,4]
 * 输出：8
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * - 2：取 [1,1]
 * - 3：取 [1,1,1]
 * - 4：取 [4]
 * - 5：取 [4,1]
 * - 6：取 [4,1,1]
 * - 7：取 [4,1,1,1]
 * 从 0 开始，你可以构造出 8 个连续整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 基础的动态规划思想，对上一步的硬币加不加得出的所有结果，总结规划，得出所有的值情况
 * 这里每一步骤是，当前硬币要不要加上上一步骤的所有状态值
 * 中间过程，硬币加不加没有限制
 * 状态值是最后判断结果，连续数目
 * @author buhuan.wang
 * @since 2023/2/8
 */
public class MakeMaxConsecutiveNum {


    public static void main(String[] args) {
        int[] coins = {1, 1, 1, 4}; // 物品重量
        int ret = 0;

        ret = getMaximumConsecutive(coins);
        System.out.println(ret);

        int[] coins2 = {1, 3, 4}; // 物品重量
        ret = getMaximumConsecutive(coins2);
        System.out.println(ret);
        ret = getMaximumConsecutive2(coins2);
        System.out.println(ret);
    }

    public static int getMaximumConsecutive(int[] coins) {
        int[] nodeWeight = coins;
        int nodeTotalNum = coins.length;
        int bagWeight = 0;
        for (int i = 0; i < coins.length; ++i) {
            bagWeight += coins[i];
        }
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

        int maxNum = 0;
        int currentNum = 0;
        for (int j = 0; j <= bagWeight; ++j) {
            if (nodeCalcStates[j] == true) {
                currentNum++;
            } else {
                if (currentNum > maxNum) {
                    maxNum = currentNum;
                }
                currentNum = 0;
            }
        }
        if (currentNum > maxNum) {
            maxNum = currentNum;
        }

        return maxNum;
    }

    public static int getMaximumConsecutive2(int[] coins) {
        int res = 1;
        Arrays.sort(coins);
        for (int i : coins) {
            if (i > res) {
                break;
            }
            res += i;
        }
        return res;
    }
}
