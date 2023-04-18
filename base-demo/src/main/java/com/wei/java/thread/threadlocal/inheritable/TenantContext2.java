package com.wei.java.thread.threadlocal.inheritable;

/**
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class TenantContext2 {
    private TenantContext2() {
    }

    private static InheritableThreadLocal<String> userHolder = new InheritableThreadLocal<>();

    public static String getUser() {
        return userHolder.get();
    }

    public static void setUser(String user) {
        userHolder.set(user);
    }

    public static void clean() {
        userHolder.remove();
    }
}
