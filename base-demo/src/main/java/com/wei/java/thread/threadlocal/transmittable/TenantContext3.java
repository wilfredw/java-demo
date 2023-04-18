package com.wei.java.thread.threadlocal.transmittable;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class TenantContext3 {
    private TenantContext3() {
    }

    private static TransmittableThreadLocal<String> userHolder = new TransmittableThreadLocal<>();

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
