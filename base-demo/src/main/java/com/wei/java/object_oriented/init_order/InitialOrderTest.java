package com.wei.java.object_oriented.init_order;

import java.util.Arrays;

public class InitialOrderTest {
    /* 静态变量 */
    public static String staticField = "staticField";

    public String instanceField = "instanceField";

    static {
        System.out.println(staticField);
        System.out.println("静态初始化块...");
    }

    {
        System.out.println(instanceField);
        System.out.println("实例初始化块...");
    }

    public InitialOrderTest() {
        System.out.println("构造函数");
    }

    public static void main(String[] args) {
        InitialOrderTest initialOrderTest = new InitialOrderTest();
        System.out.println("initialOrderTest = " + initialOrderTest.instanceField);
    }
}
