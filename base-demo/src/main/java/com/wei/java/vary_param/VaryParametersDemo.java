package com.wei.java.vary_param;

import com.wei.java.util.SystemOutUtil;

public class VaryParametersDemo {
    public static void main(String[] args) {
        SystemOutUtil.println("hello");
        testParam();
        testParam(1);
        testParam(1, "1");
    }

    public static void testParam(Object... objects) {
        SystemOutUtil.println("=== testParam: " + objects.length);
        for (Object o : objects) {
            SystemOutUtil.println("param: " + o);
        }
    }
}
