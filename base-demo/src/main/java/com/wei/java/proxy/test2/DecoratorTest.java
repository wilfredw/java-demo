package com.wei.java.proxy.test2;

public class DecoratorTest implements Test{
    private Test target;

    public DecoratorTest(Test target) {
        this.target = target;
    }

    public int test(int i) {
        return target.test(i);
    }
}
