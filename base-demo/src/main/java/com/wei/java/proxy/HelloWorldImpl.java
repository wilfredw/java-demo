package com.wei.java.proxy;

/**
 * Created by viruser on 2019/6/11.
 */
public class HelloWorldImpl implements HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
