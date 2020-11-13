package com.wei.java.proxy.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest implements InvocationHandler {
    private Test target;

    private JdkProxyTest(Test target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    public static Test newProxyInstance(Test target) {
        return (Test) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(),
                new Class<?>[]{Test.class},
                new JdkProxyTest(target));
    }
}
