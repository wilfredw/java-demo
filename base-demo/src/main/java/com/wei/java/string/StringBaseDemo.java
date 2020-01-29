package com.wei.java.string;

import com.wei.java.util.SystemOutUtil;

public class StringBaseDemo {
    public static void main(String[] args) {
        String x = "[aa.kklk\\akk\\kll.kk]";
        SystemOutUtil.println("before : " + x);
        String newStr = x.replace(".kk", "+++");
        SystemOutUtil.println("after  : " + x);
        SystemOutUtil.println(newStr);

        x = "[aa.kklk\\akk\\kll.kk]";
        SystemOutUtil.println("before : " + x);
        newStr = x.replaceAll(".kk", "+++");
        SystemOutUtil.println("after  : " + x);
        SystemOutUtil.println(newStr);

        x = "[aaakklk\\akk\\kll.kk]";
        SystemOutUtil.println("before : " + x);
        newStr = x.replaceFirst(".kk", "+++");
        SystemOutUtil.println("after  : " + x);
        SystemOutUtil.println(newStr);
    }
}
