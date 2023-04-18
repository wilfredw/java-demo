package com.wei.java.thread.threadlocal.inheritable;

import com.wei.java.thread.threadlocal.java.User;

/**
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class UserContext2 {
    private UserContext2() {
    }

    private static InheritableThreadLocal<User> userHolder = new InheritableThreadLocal<>();

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
