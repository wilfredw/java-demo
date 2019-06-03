package com.wei.java.reflect;

import java.lang.reflect.Constructor;

/**
 * Created by viruser on 2019/6/1.
 */
public class ReflectDemo {
    public static void main(String[] args) {

        Class<?> classDemo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        try {
            classDemo1 = Class.forName("com.wei.java.reflect.ReflectDemo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        demo2 = new ReflectDemo().getClass();
        demo3 = ReflectDemo.class;


        System.out.println("类名称   "+classDemo1.getName());
        System.out.println("类名称   "+demo2.getName());
        System.out.println("类名称   "+demo3.getName());

        System.out.println("获取构造函数，生成实例");
        ReflectDemo reflectDemo = null;
        ReflectDemo reflectDemo1 = null;
        ReflectDemo reflectDemo2 = null;
        ReflectDemo reflectDemo3 = null;
        Constructor<?> constructors[] = classDemo1.getConstructors();
        //取得全部的构造函数
        try {
            reflectDemo = (ReflectDemo)classDemo1.newInstance();

            reflectDemo1 = (ReflectDemo) constructors[0].newInstance();
            reflectDemo2 = (ReflectDemo) constructors[1].newInstance(100);
            reflectDemo3 = (ReflectDemo) constructors[2].newInstance(1, 2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reflectDemo.setX(10);
        reflectDemo.setY(20);

        reflectDemo.print();
        reflectDemo1.print();
        reflectDemo2.print();
        reflectDemo3.print();


    }

    public ReflectDemo(int x) {
        this.x = x;
    }


    public ReflectDemo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ReflectDemo() {

    }


    private int x;
    private int y;
    private String comment;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void print() {
        System.out.println("Hello World!" + x + " - " + y + " - "
                + comment + " " + this);
    }
}
