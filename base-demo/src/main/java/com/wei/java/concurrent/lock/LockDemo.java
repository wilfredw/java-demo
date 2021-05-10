package com.wei.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的Demo
 *
 * @author buhuan.wang
 * @since 2021/5/6
 */
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("a");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
