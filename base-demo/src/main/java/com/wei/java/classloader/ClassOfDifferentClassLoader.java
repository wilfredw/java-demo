package com.wei.java.classloader;

import java.io.InputStream;

public class ClassOfDifferentClassLoader {
    public static void main(String[] args) {
        ClassLoader myLoad = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        System.out.println("super load " + name);
                        return super.loadClass(name);
                    }
                    System.out.println("myself load " + name);
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Throwable e) {
                    System.out.println(e);
                }
                return null;
            }
        };
        Object object = null;
        try {
            object = myLoad.loadClass("com.wei.java.classloader.ClassOfDifferentClassLoader").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object.getClass());
        System.out.println(object instanceof com.wei.java.classloader.ClassOfDifferentClassLoader);


        System.out.println("--------------- 2 ----------");
        ClassLoader myLoad2 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
        Object object2 = null;
        try {
            object2 = myLoad2.loadClass("com.wei.java.classloader.ClassOfDifferentClassLoader").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object2.getClass());
        System.out.println(object2 instanceof com.wei.java.classloader.ClassOfDifferentClassLoader);
    }
}
