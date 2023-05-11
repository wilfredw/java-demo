package com.wei.java.memory;

/**
 * VM Args: -Xss128k
 */
public class StackOverflowErrorDemo {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackOverflowErrorDemo oom = new StackOverflowErrorDemo();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack len: " + oom.stackLength);
            throw e;
        }
    }
}
