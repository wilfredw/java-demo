package com.wei.java.proxy.test;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class DynamicProxyPerformanceTest {

    static CountService jdkProxy;

    static CountService cglibProxy;

    static {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() throws Exception {
        CountService delegate = new CountServiceImpl();

        long time = System.currentTimeMillis();
        jdkProxy = createJdkDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create JDK Proxy: " + time + " ms");

        time = System.currentTimeMillis();
        cglibProxy = createCglibDynamicProxy(delegate);
        time = System.currentTimeMillis() - time;
        System.out.println("Create CGLIB Proxy: " + time + " ms");
    }

    public static void main(String[] args) throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".//");
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Options opt = new OptionsBuilder()
                .include(DynamicProxyPerformanceTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void cglibProxy() {
        int count = 1000000;
        for (int i = 0; i < count; i++) {
            cglibProxy.count();
            cglibProxy.test1();
            cglibProxy.test2();
            cglibProxy.test3();
            cglibProxy.test4();
            cglibProxy.test5();
            cglibProxy.test6();
            cglibProxy.test7();
            cglibProxy.test8();
            cglibProxy.test9();
            cglibProxy.t0();
            cglibProxy.t1();
            cglibProxy.t2();
            cglibProxy.t3();
            cglibProxy.t4();
            cglibProxy.t5();
            cglibProxy.t6();
            cglibProxy.t7();
            cglibProxy.t8();
            cglibProxy.t9();
            cglibProxy.a1();
            cglibProxy.a2();
            cglibProxy.a3();
            cglibProxy.a4();
            cglibProxy.a5();
            cglibProxy.a6();
            cglibProxy.a7();
            cglibProxy.a8();
            cglibProxy.a9();
        }
    }

    @Benchmark
    public void testjdkProxy() {
        int count = 1000000;
        for (int i = 0; i < count; i++) {
            jdkProxy.count();
            jdkProxy.test1();
            jdkProxy.test2();
            jdkProxy.test3();
            jdkProxy.test4();
            jdkProxy.test5();
            jdkProxy.test6();
            jdkProxy.test7();
            jdkProxy.test8();
            jdkProxy.test9();
            jdkProxy.t0();
            jdkProxy.t1();
            jdkProxy.t2();
            jdkProxy.t3();
            jdkProxy.t4();
            jdkProxy.t5();
            jdkProxy.t6();
            jdkProxy.t7();
            jdkProxy.t8();
            jdkProxy.t9();
            jdkProxy.a1();
            jdkProxy.a2();
            jdkProxy.a3();
            jdkProxy.a4();
            jdkProxy.a5();
            jdkProxy.a6();
            jdkProxy.a7();
            jdkProxy.a8();
            jdkProxy.a9();
        }
    }

    private static <T extends CountService> CountService createJdkDynamicProxy(
            final CountService delegate) {
        CountService jdkProxy = (CountService) Proxy
                .newProxyInstance(ClassLoader.getSystemClassLoader(),
                        new Class[] { CountService.class },
                        new JdkHandler(delegate));
        return jdkProxy;
    }

    private static class JdkHandler implements InvocationHandler {

        final Object delegate;

        JdkHandler(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object object, Method method, Object[] objects)
                throws Throwable {
            return method.invoke(delegate, objects);
        }
    }

    private static CountService createCglibDynamicProxy(
            final CountService delegate) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
//        enhancer.setSuperclass(CountServiceImpl.class);
        enhancer.setInterfaces(new Class[] { CountService.class });
        CountService cglibProxy = (CountService) enhancer.create();
        return cglibProxy;
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        CglibInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        public Object intercept(Object object, Method method, Object[] objects,
                                MethodProxy methodProxy) throws Throwable {
            return methodProxy.invoke(delegate, objects);
        }
    }
}