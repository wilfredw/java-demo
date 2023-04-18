package com.wei.java.thread.threadlocal.java;

/**
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class UserContext {
    private UserContext() {
    }

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static User getUser() {
        return userHolder.get();
    }

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static void clean() {
        userHolder.remove();
    }
}
