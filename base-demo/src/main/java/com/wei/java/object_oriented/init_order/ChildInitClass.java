package com.wei.java.object_oriented.init_order;

import java.util.Arrays;

public class ChildInitClass extends ParentInitClass {
    public static String childStaticField = "childStaticField";
    public String childField = "childField";
    protected int x = 0;
    protected int y = 0;
    static {
        System.out.println(childStaticField);
        System.out.println("ChildInitClass.static initializer");
    }
    {
        System.out.println(childField);
        System.out.println("ChildInitClass.instance initializer");
    }
    public ChildInitClass() {
        System.out.println("ChildInitClass.ChildInitClass");
        System.out.println("i: " + i +" j: "+ j);
    }
    public static void main(String[] args) {
        System.out.println("ChildInitClass.main");
        ChildInitClass childInitClass = new ChildInitClass();
        System.out.println("childInitClass = " + childInitClass);
    }
}
