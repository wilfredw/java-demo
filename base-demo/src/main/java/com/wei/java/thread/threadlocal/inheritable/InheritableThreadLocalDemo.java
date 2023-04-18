package com.wei.java.thread.threadlocal.inheritable;

import com.wei.java.thread.threadlocal.java.User;
import org.junit.Assert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/a3134d70acf7
 *
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        User userEntity = new User();
        userEntity.setName("mzt");
        UserContext2.setUser(userEntity);

        User user = UserContext2.getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "mzt");
        System.out.println("user " + user);
        new Thread(() -> {
            // 子线程继承父线程
            final User user1 = UserContext2.getUser();
            System.out.println("user1 " + user1);
            Assert.assertNotNull(user1);
            Assert.assertEquals(user1.getName(), "mzt");
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ExecutorService ttlExecutorService = Executors.newFixedThreadPool(1);
        TenantContext2.setUser("mzt_" + 1);
        // 这里只在线程创建，第一次threadlocal不存在，才会拷贝父线程
        ttlExecutorService.submit(() -> {
            String tenantId = TenantContext2.getUser();
            System.out.println("#########, 1 " + tenantId);
            //TenantContext.clean();
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这里ExecutorService线程已经创建，threadloca以及存在，不会拷贝
        TenantContext2.setUser("mzt_" + 2);
        ttlExecutorService.submit(() -> {

            System.out.println("#########, 2 " + TenantContext2.getUser());
            // TenantContext.clean();
        });

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
