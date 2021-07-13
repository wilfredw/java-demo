package com.wei.java.middleware.lock.distributed;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * Redis 分布式锁
 *
 * @author buhuan.wang
 * @since 2021/6/7
 */
public class RedisReentrantLock implements Lock {
    private static final String DEFAULT_CONNECTOR = "-";
    private static final Integer LEASE_TIME = 3;

    private Jedis jedis;
    private String key;
    private String id;
    private ExpirationRenewalTask expirationRenewalTask = new ExpirationRenewalTask();

    public RedisReentrantLock(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
        this.id = generateId();
    }

    @Override
    public void lock() {
        boolean ret = false;
        try {
            long current = System.currentTimeMillis();
            String script =
                    "if (redis.call('exists', KEYS[1]) == 0) then " +
                            "redis.call('hset', KEYS[1], ARGV[2], 1); " +
                            "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                            "return nil; " +
                            "end; " +
                            "if (redis.call('hexists', KEYS[1], ARGV[2]) >= 1) then " +
                            "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                            "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                            "return nil; " +
                            "end; " +
                            "return redis.call('pttl', KEYS[1]);";

            Long expire = (Long) jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(LEASE_TIME.toString(), id));
            if (expire == null) {
//                log.info("[Loki] get lock key - {} by thread - {} cost {}.", key, id, System.currentTimeMillis() - current);
                ret = true;
            } else {
                Semaphore semaphore = new Semaphore(0);
                while (true) {
                    expire = (Long) jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(LEASE_TIME.toString(), id));
                    if (expire == null) {
//                        log.info("[Loki] get lock key - {} by thread - {} cost {}.", key, id, System.currentTimeMillis() - current);
                        ret = true;
                    }
                    if (expire >= 0) {
                        semaphore.tryAcquire(Math.min(expire, 100), TimeUnit.MILLISECONDS);
                    }
                }
            }
        } catch (Exception e) {
//            log.error("[Loki] get lock failure.", e);
            ret = false;
        }
        if (ret) {
            startExpirationRenewalTask();
        }
//        return ret;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new RuntimeException("not supported");
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            stopExpirationRenewalTask();
            long current = System.currentTimeMillis();
            String script =
                    "if (redis.call('exists', KEYS[1]) == 0) then " +
                            "return nil; " +
                            "end; " +
                            "if (redis.call('hexists', KEYS[1], ARGV[2]) >= 1) then " +
                            "local counter = redis.call('hincrby', KEYS[1], ARGV[1], -1); " +
                            "if (counter <= 0) then " +
                            "redis.call('del', KEYS[1]); " +
                            "return 0; " +
                            "else " +
                            "return counter;" +
                            "end; " +
                            "return nil; " +
                            "end; " +
                            "return nil;";
            Long expire = (Long) jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(generateId()));
            if (expire == null) {
//                log.error();
            }
//            log.info("[Loki] Release lock key - {} by thread - {} cost {}.", key, generateId(), System.currentTimeMillis() - current);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Condition newCondition() {
        throw new RuntimeException("not supported");
    }

    private void startExpirationRenewalTask() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                ScheduledThreadPoolExecutorSingletonHolder.scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.schedule(expirationRenewalTask, 1, TimeUnit.SECONDS);
    }

    private void stopExpirationRenewalTask() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                ScheduledThreadPoolExecutorSingletonHolder.scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.schedule(expirationRenewalTask, 1, TimeUnit.SECONDS);
    }


    private class ExpirationRenewalTask implements Runnable {

        @Override
        public void run() {
            String script =
                    "if (redis.call('exists', KEYS[1]) == 0) then " +

                            "return nil; " +
                            "end; " +
                            "if (redis.call('hexists', KEYS[1], ARGV[2]) >= 1) then " +
                            "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                            "return redis.call('pttl', KEYS[1]);  " +
                            "end; " +
                            "return nil;";

            Long expire = (Long) jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(LEASE_TIME.toString(), id));
            if (expire != null && expire > 0) {

            } else {
//                log.error();
            }

        }
    }

    private static class ScheduledThreadPoolExecutorSingletonHolder {
        public static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor
                = new ScheduledThreadPoolExecutor(1);

        private ScheduledThreadPoolExecutorSingletonHolder() {
        }

    }

    private String generateId() {
        String id = Joiner.on(DEFAULT_CONNECTOR).join(getLocalHost(), getProcessId(), Thread.currentThread().getId());
        return id;
    }

    public String getLocalHost() {
        return "";
    }

    public String getProcessId() {
        return "";
    }


}
