package com.wei.java.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by viruser on 2019/6/11.
 */
public class HelloInvocationHandler implements InvocationHandler {
    private Object target;

    public HelloInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("HelloInvocationHandler invoke before...");
        System.out.println("invoke method: " + method);
        Object ret = method.invoke(target, args);
        System.out.println("HelloInvocationHandler invoke after...");
        return ret;
    }
}
