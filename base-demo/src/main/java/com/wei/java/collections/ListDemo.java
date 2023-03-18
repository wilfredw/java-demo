package com.wei.java.collections;

import java.util.ArrayList;
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
    }
}
