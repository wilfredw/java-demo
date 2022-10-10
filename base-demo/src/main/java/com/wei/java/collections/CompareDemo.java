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
        compareList();
        System.out.println("==========");
        compareTreeMap();
    }

    public static void compareTreeMap() {
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(7, "a");
        treeMap.put(9, "a");
        treeMap.put(3, "b");
        treeMap.put(1, "a");
        treeMap.put(5, "c");
        treeMap.put(4, "a");
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey());
        }

        System.out.println("==========");

        Map<Integer, String> treeMap2 = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2) * -1;
            }
        });
        treeMap2.put(7, "a");
        treeMap2.put(9, "a");
        treeMap2.put(3, "b");
        treeMap2.put(1, "a");
        treeMap2.put(5, "c");
        treeMap2.put(4, "a");
        for (Map.Entry<Integer, String> entry : treeMap2.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static void compareList() {
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
