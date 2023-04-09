package com.wei.java.string;

import java.io.UnsupportedEncodingException;

/**
 * @author buhuan.wang
 * @since 2023/4/9
 */
public class StringAndCharLength {

    public static void main(String[] args) {
        try {
            // jdk8中string底层用char数组存储，jdk9以后用byte[]存储
            // string的length等于Character长度
            // char等于2个byte
            
            // 中文常见字
            String s = "你好";
            System.out.println("1. string length =" + s.length());
            System.out.println("1. string utf-8 bytes length =" + s.getBytes("utf-8").length);
            System.out.println("1. string gbk bytes length =" + s.getBytes("gbk").length);
            System.out.println("1. string char length =" + s.toCharArray().length);
            System.out.println();
            // emojis
            s = "👦👩";
            System.out.println("2. string length =" + s.length());
            System.out.println("2. string utf-8 bytes length =" + s.getBytes("utf-8").length);
            System.out.println("2. string gbk bytes length =" + s.getBytes("gbk").length);
            System.out.println("2. string char length =" + s.toCharArray().length);
            System.out.println();
            // 中文生僻字
            s = "𡃁妹";
            System.out.println("3. string length =" + s.length());
            System.out.println("3. string utf-8 bytes length =" + s.getBytes("utf-8").length);
            System.out.println("3. string gbk bytes length =" + s.getBytes("gbk").length);
            System.out.println("3. string char length =" + s.toCharArray().length);
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
