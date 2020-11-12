package com.wei.java.enums;

public enum TestEnums {
    CREATED("created"),
    ADAPTERED("adapted"),
    PROCESSED("processed");

    private String code;
    private TestEnums prev;
    private TestEnums next;

    private TestEnums(String code) {
        this.code = code;
        this.prev = null;
        this.next = null;
    }

    static {
        TestEnums[] values = TestEnums.values();
        TestEnums prev = null;
        for (TestEnums item : values) {
            if (prev != null) {
                item.prev = prev;
                prev.next = item;
            }
            prev = item;
        }

    }

    public static void main(String[] args) {
        TestEnums[] values = TestEnums.values();
        TestEnums prev = null;
        for (TestEnums item : values) {
            System.out.println(item.code);
        }
    }
}
