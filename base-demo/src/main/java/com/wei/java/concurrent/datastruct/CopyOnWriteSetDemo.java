package com.wei.java.concurrent.datastruct;

import com.alibaba.fastjson.JSON;
import com.wei.java.util.SystemOutUtil;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteSetDemo {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> cowAL = new CopyOnWriteArraySet<>();
        cowAL.add("hello");
        cowAL.add("hello");
        SystemOutUtil.println("" + cowAL.size());
        SystemOutUtil.println(JSON.toJSONString(cowAL));
        cowAL.add("hello");
        cowAL.add("b");
        SystemOutUtil.println("" + cowAL.size());
        SystemOutUtil.println(JSON.toJSONString(cowAL));
        cowAL.remove("hello");
        SystemOutUtil.println("" + cowAL.size());
        SystemOutUtil.println(JSON.toJSONString(cowAL));

    }
}
