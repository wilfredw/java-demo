package com.wei.java.proxy.test2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibInterfaceProxyTest implements MethodInterceptor {
    private Object target;

    public CglibInterfaceProxyTest(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invoke(this.target, args);
    }

    public static <T extends Test> Test newProxyInstance(T target, Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterfaceProxyTest(target));
        enhancer.setInterfaces(new Class[]{targetClass});
        return (Test)enhancer.create();
    }

}
