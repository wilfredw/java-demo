package com.wei.java.junit;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/1/11
 */
public class JunitApplication {
    public static void main(String[] args) {
        RunService runService = new RunService();
        System.out.println(runService.calc(1, 2));
    }
}
