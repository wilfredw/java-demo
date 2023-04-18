package com.wei.java.thread.threadlocal.java;

import org.junit.Assert;

import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/a3134d70acf7
 *
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        User userEntity = new User();
        userEntity.setName("mzt");
        UserContext.setUser(userEntity);

        User user = UserContext.getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "mzt");
        new Thread(() -> {
            // 子线程无法继承父线程
            final User user1 = UserContext.getUser();
            Assert.assertNotNull(user1);
            Assert.assertEquals(user1.getName(), "mzt");
        }).start();
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
