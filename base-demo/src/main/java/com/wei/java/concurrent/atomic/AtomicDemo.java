package com.wei.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author buhuan.wang
 * @since 2021/5/10
 */
public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(2);
        atomicInteger.compareAndSet(2, 3);
        System.out.println(atomicInteger);

        User user = new User();
        user.setAge(2);
        user.setName("ha");
        System.out.println(user.getAge());
    }

    public static class User {
        private volatile int age = 1;
        private volatile String name;
        AtomicIntegerFieldUpdater ageUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        AtomicReferenceFieldUpdater nameUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");

        public void setAge(Integer newV) {
            ageUpdater.compareAndSet(this, this.age, newV);
        }

        public Integer getAge() {
            return age;
        }

        public void setName(String newName) {
            nameUpdater.compareAndSet(this, this.name, newName);
        }
    }
}
