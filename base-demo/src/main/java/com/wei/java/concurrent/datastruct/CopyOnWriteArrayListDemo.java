package com.wei.java.concurrent.datastruct;

import com.alibaba.fastjson.JSON;
import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cowAL = new CopyOnWriteArrayList<>();
        cowAL.add("hello");
        cowAL.add("hello");
        SystemOutUtil.println("" + cowAL.size());
        SystemOutUtil.println(cowAL.get(cowAL.size() - 1));
        SystemOutUtil.println(JSON.toJSONString(cowAL));
        cowAL.addIfAbsent("hello");
        cowAL.addIfAbsent("b");
        SystemOutUtil.println("" + cowAL.size());
        SystemOutUtil.println(cowAL.get(cowAL.size() - 1));
        SystemOutUtil.println(JSON.toJSONString(cowAL));

    }
}
