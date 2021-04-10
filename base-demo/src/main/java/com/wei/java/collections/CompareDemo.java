package com.wei.java.collections;

import java.util.*;

/**
 * Compare
 *
 * @author buhuan.wang
 * @since 2021/4/1
 */
public class CompareDemo {
    public static void main(String[] args) {
        List<String> validUids = new ArrayList<>(Arrays.asList("2", "1", "10", "9"));
        // 升序排列
        Collections.sort(validUids, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println(o1 + " " + o2 + " compare result: " + o1.compareTo(o2));
                return o1.compareTo(o2);
            }
        });
        System.out.println(validUids);

        List<Integer> ids = new ArrayList<>(Arrays.asList(3, 1, 5, 2));
        // 升序排列
        Collections.sort(ids, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1 + " " + o2 + " compare result: " + o1.compareTo(o2));
                return o1.compareTo(o2);
            }
        });
        System.out.println(ids);
    }
}
