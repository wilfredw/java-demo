package com.wei.java.collections;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/6/28
 */
public class ListDemo {
    public static void main(String[] args) {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("a");
        userIdList.add("b");
        userIdList.add("c");
        userIdList.add("d");
        List<String> subList = userIdList.subList(2, 3);
        System.out.println(userIdList.size());
        System.out.println(userIdList.get(0));
        System.out.println(subList);

        System.out.println("-----------");
        Iterator<String> iterator = userIdList.iterator();
        while (iterator.hasNext()) {
            String i = iterator.next();
            if (i.equalsIgnoreCase("b")) {
                System.out.println("remove " + i);
                iterator.remove();
            } else {
                System.out.println(i);
            }
        }
        System.out.println("final: " + userIdList);

        ArrayList<Integer> nums = new ArrayList<>(4);
        nums.add(9);
        nums.add(4);
        nums.add(3);
        nums.add(1);
        Collections.sort(nums);
        System.out.println(JSON.toJSONString(nums));

    }
}
