package com.wei.java.algorithm.sort;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * <p>
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsNearbyAlmostDuplicate {
    public static void main(String[] args) {
        System.out.println("==== case 1 ====");
        int[] nums = new int[] {1, 5, 9, 1, 5, 9};
        int k = 2;
        int t = 3;
        boolean ret = containsNearbyAlmostDuplicateV2(nums, k, t);
        System.out.println(ret);
        ret = containsNearbyAlmostDuplicateV3(nums, k, t);
        System.out.println(ret);

        System.out.println("==== case 2 ====");
        nums = new int[] {-3, 3};
        k = 2;
        t = 4;
        ret = containsNearbyAlmostDuplicateV2(nums, k, t);
        System.out.println(ret);
        ret = containsNearbyAlmostDuplicateV3(nums, k, t);
        System.out.println(ret);
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int num = 0;
        Deque<Integer> deque = new ArrayDeque<>(indexDiff + 1);
        deque.addLast(nums[0]);
        num = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (num >= (indexDiff + 1)) {
                deque.removeFirst();
                num--;
            }
            if (checkIfDuplicate(nums[i], deque, valueDiff)) {
                return true;
            }
            deque.addLast(nums[i]);
            num++;
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicateV3(int[] nums, int indexDiff, int valueDiff) {
        int num = 0;
        TreeSet<Integer> numSet = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Integer ceilingNum = numSet.ceiling(nums[i]);
            if (ceilingNum != null && Math.abs(ceilingNum - nums[i]) <= valueDiff) {
                return true;
            }
            Integer floorNum = numSet.floor(nums[i]);
            if (floorNum != null && Math.abs(floorNum - nums[i]) <= valueDiff) {
                return true;
            }
            numSet.add(nums[i]);
            if (numSet.size() > indexDiff) {
                numSet.remove(nums[i - indexDiff]);
            }
        }
        return false;
    }


    public static boolean containsNearbyAlmostDuplicateV2(int[] nums, int indexDiff, int valueDiff) {
        int num = 0;
        Map<Integer, Deque<Integer>> buket = new TreeMap<>();
        addToBuket(nums[0], buket, valueDiff);
        num = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (num >= (indexDiff + 1)) {
                removeFromBuket(nums[i - indexDiff - 1], buket, valueDiff);
                num--;
            }
            if (checkIfDuplicateInBuket(nums[i], buket, valueDiff)) {
                return true;
            }
            addToBuket(nums[i], buket, valueDiff);
            num++;
        }
        return false;
    }

    public static boolean checkIfDuplicateInBuket(Integer value, Map<Integer, Deque<Integer>> buket, Integer valueDiff) {
        Integer buketSize = (valueDiff + 1);
        Integer currentBuketNum = Double.valueOf(Math.floor(value * 1.0 / buketSize)).intValue();
        if (buket.get(currentBuketNum) != null && buket.get(currentBuketNum).size() > 0) {
            return true;
        }
        Deque<Integer> deque = buket.get(currentBuketNum - 1);
        if (deque != null && deque.size() > 0) {
            if (checkIfDuplicate(value, deque, valueDiff)) {
                return true;
            }
        }
        deque = buket.get(currentBuketNum + 1);
        if (deque != null && deque.size() > 0) {
            if (checkIfDuplicate(value, deque, valueDiff)) {
                return true;
            }
        }
        return false;
    }

    public static void addToBuket(Integer value, Map<Integer, Deque<Integer>> buket, Integer valueDiff) {
        Integer buketNum = Double.valueOf(Math.floor(value * 1.0 / (valueDiff + 1))).intValue();
        Deque<Integer> valueList = buket.get(buketNum);
        if (valueList == null) {
            valueList = new ArrayDeque<>();
            buket.put(buketNum, valueList);
        }
        valueList.addLast(value);
    }

    public static void removeFromBuket(Integer value, Map<Integer, Deque<Integer>> buket, Integer valueDiff) {
        Integer buketNum = Double.valueOf(Math.floor(value * 1.0 / (valueDiff + 1))).intValue();
        Deque<Integer> valueList = buket.get(buketNum);
        valueList.removeFirst();
    }

    public static boolean checkIfDuplicate(Integer target, Deque<Integer> deque, int valueDiff) {
        for (Integer i : deque) {
            if (Math.abs(i - target) <= valueDiff) {
                return true;
            }
        }
        return false;
    }


}
