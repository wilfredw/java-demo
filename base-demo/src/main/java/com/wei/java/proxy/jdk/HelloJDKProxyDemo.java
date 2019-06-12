package com.wei.java.proxy.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by viruser on 2019/6/11.
 */
public class HelloJDKProxyDemo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
        //目标运行对象
        HelloWorld target = new HelloWorldImpl();
        //生成代理方法的接口对象
        final InvocationHandler myHandler = new HelloInvocationHandler(target);
        //生成最终代理类
        Class<?> proxyClass = Proxy.getProxyClass(HelloJDKProxyDemo.class.getClassLoader(),
                HelloWorld.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        HelloWorld helloWorld = (HelloWorld)cons.newInstance(myHandler);
        helloWorld.sayHello();

        //下面是更简单的一种写法，本质上和上面是一样的
//        HelloWorld helloWorld = (HelloWorld)Proxy.newProxyInstance(HelloJDKProxyDemo.class.getClassLoader(),
//                new Class<?>[]{HelloWorld.class},
//                new HelloInvocationHandler(new HelloWorldImpl()));
//        helloWorld.sayHello();
    }
}
