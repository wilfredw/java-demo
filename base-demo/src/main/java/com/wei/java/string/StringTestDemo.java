package com.wei.java.string;

public class StringTestDemo {
    public static void main(String[] args) {
        String test = "this is apple";
        String readStr = "this";
        int x = test.indexOf(readStr);
        int startIndex = 0;
        if (startIndex < x) {
            String prefixStr = test.substring(startIndex, x);
            System.out.println(prefixStr);
            startIndex = x;
        }
        System.out.println(readStr);
        startIndex = startIndex + readStr.length();
        if (startIndex < test.length()) {
            String postStr = test.substring(startIndex, test.length());
            System.out.println(postStr);
            startIndex = test.length();
        }
        System.out.println(x);
    }
}
