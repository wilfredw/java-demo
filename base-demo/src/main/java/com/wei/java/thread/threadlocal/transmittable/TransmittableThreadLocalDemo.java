package com.wei.java.thread.threadlocal.transmittable;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/a3134d70acf7
 *
 * @author buhuan.wang
 * @since 2023/4/18
 */
public class TransmittableThreadLocalDemo {
    public static void main(String[] args) {

        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(
                Executors.newFixedThreadPool(1));
        TenantContext3.setUser("mzt_" + 1);
        // 包了TtlRunnable， 每次run都会拷贝
        ttlExecutorService.submit(() -> {
            String tenantId = TenantContext3.getUser();
            System.out.println("#########, 1 " + tenantId);
            //TenantContext.clean();
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 包了TtlRunnable， 每次run都会拷贝
        TenantContext3.setUser("mzt_" + 2);
        ttlExecutorService.submit(() -> {

            System.out.println("#########, 2 " + TenantContext3.getUser());
            // TenantContext.clean();
        });

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
