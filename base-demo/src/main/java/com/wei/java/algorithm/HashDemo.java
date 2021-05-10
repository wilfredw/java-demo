package com.wei.java.algorithm;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Java中的Hash使用
 *
 * @author buhuan.wang
 * @since 2021/5/10
 */
public class HashDemo {
    public static void main(String[] args) {
        String name = "abc123";
        System.out.println(name.hashCode());

        try {
            // 创建一个MessageDigest实例:
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 反复调用update输入数据:
            md.update("Hello".getBytes("UTF-8"));
            md.update("World".getBytes("UTF-8"));
            byte[] result = md.digest(); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
            System.out.println(new BigInteger(1, result).toString(16));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
