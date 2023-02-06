package com.wei.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;

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

        LongAdder longAdder = new LongAdder();
        longAdder.add(2L);
        System.out.println("longAdder: " + longAdder.longValue());

        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + 2 * right;
            }
        }, 1L);
        longAccumulator.accumulate(2L);
        System.out.println("longAccumulator: " + longAccumulator.longValue());

        User user = new User();
        user.setAge(2);
        user.setName("ha");
        System.out.println("user: " + user.getAge());


        testReference();

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

    public static void testReference() {
        System.out.println("=========== reference begin ========");

        AtomicReference<Record> recordAtomicReference = new AtomicReference<>();
        Record record1 = new Record(1, "a");
        recordAtomicReference.set(record1);
        System.out.println("record 1: " + recordAtomicReference.get().toString());
        Record record2 = new Record(2, "b");
        recordAtomicReference.compareAndSet(record1, record2);
        System.out.println("record 2: " + recordAtomicReference.get().toString());
        Record record3 = new Record(3, "c");
        Record resultRecord = recordAtomicReference.accumulateAndGet(record3, new BinaryOperator<Record>() {
            @Override
            public Record apply(Record record, Record record2) {
                Record ret = new Record(record.getStatus() + record2.getStatus(), record.getValue() + record2.getValue());
                return ret;
            }
        });
        System.out.println("record 3: " + resultRecord);
        System.out.println("record 4: " + recordAtomicReference.get());

        int stamp1 = 1;
        int stamp2 = 2;
        AtomicStampedReference<Record> recordAtomicStampedReference = new AtomicStampedReference<>(record1, stamp1);
        boolean ret = recordAtomicStampedReference.compareAndSet(record1, record2, stamp1, stamp2);
        System.out.println("AtomicStampedReference 1. ret:" + ret);
        int[] stampHolder = new int[1];
        Record current = recordAtomicStampedReference.get(stampHolder);
        System.out.println("AtomicStampedReference 2. record: " + current + "  stamp: " + stampHolder[0]);

        System.out.println("=========== reference over ========");
    }

    public static class Record {
        private int status;
        private String value;

        public Record(int status, String value) {
            this.status = status;
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + status + ":-:" + value;
        }
    }
}
