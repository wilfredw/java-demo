package com.wei.java.algorithm.sort;

import com.alibaba.fastjson.JSON;

public class QuickSortDemo {
    public static void main(String[] args) {
        int[] nums = new int[]{9, 1, 3, 7, 6, 3, 2, 5, 2, 5};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(JSON.toJSONString(nums));
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        swapEndWithMidNum(nums, start, end);
        int partitionIndex = start;
        for (int i = start; i < end; ++i) {
            if (nums[i] < nums[end]) {
                swapNum(nums, i, partitionIndex);
                partitionIndex++;
            }
        }
        swapNum(nums, partitionIndex, end);

        quickSort(nums, start, partitionIndex);
        quickSort(nums, partitionIndex + 1, end);
    }

    public static void swapEndWithMidNum(int[] nums, int start, int end) {
        if ((end - start) > 3) {
            int midIndex = (start + end) / 2;
            if (nums[start] > nums[end]) {
                if (nums[midIndex] > nums[end] && nums[midIndex] < nums[start]) {
                    swapNum(nums, end, midIndex);
                } else if (nums[midIndex] >= nums[start]) {
                    swapNum(nums, start, end);
                } // else nums[midIndex] <= nums[end] do nothing
            } else { // nums[start] <= nums[end]
                if (nums[midIndex] < nums[end] && nums[midIndex] > nums[start]) {
                    swapNum(nums, end, midIndex);
                } else if (nums[midIndex] <= nums[start]) {
                    swapNum(nums, start, end);
                } // else nums[midIndex] >= nums[end] do nothing
            }
        }
    }

    public static void swapNum(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
