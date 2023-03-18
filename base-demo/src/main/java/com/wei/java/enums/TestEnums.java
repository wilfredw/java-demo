package com.wei.java.enums;

import java.util.HashMap;
import java.util.Map;

public enum TestEnums {


    CREATED("created"),

    ADAPTERED("adapted"),

    PROCESSED("processed");

    private String code;
    private TestEnums prev;
    private TestEnums next;
    public static Map<String, TestEnums> codeToEnum;

    private TestEnums(String code) {
        this.code = code;
        this.prev = null;
        this.next = null;
    }

    static {

        TestEnums[] values = TestEnums.values();
        codeToEnum = new HashMap<>(values.length);
        TestEnums prev = null;
        for (TestEnums item : values) {
            if (prev != null) {
                item.prev = prev;
                prev.next = item;
            }
            prev = item;

            codeToEnum.put(item.code, item);
        }

    }

    public static void main(String[] args) {
        TestEnums[] values = TestEnums.values();
        TestEnums prev = null;
        for (TestEnums item : values) {
            System.out.println(item.code);
        }
        System.out.println(TestEnums.codeToEnum.keySet());
    }
}
