package com.wei.java.object_oriented.init_order;

public class ParentInitClass {
    public static String parentStaticField = "parentStaticField";
    public String parentField = "parentField";
    protected int i = 0;
    protected int j = 0;
    static {
        System.out.println(parentStaticField);
        System.out.println("ParentInitClass.static initializer");
    }
    {
        System.out.println(parentField);
        System.out.println("ParentInitClass.instance initializer");
    }
    public ParentInitClass() {
        System.out.println("ParentInitClass.ParentInitClass");
        System.out.println("i + j: " + (i + j));
        j = 20;
    }
}
