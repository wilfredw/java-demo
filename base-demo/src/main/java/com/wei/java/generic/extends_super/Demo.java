package com.wei.java.generic.extends_super;

import java.util.ArrayList;
import java.util.List;

/*
<? extends E> 上限通配符，用来限制类型的上限
<? super E> 下限通配符，用来限制类型的下限

先举几个类的例子：
基类： A
A的子类： A1, A2
A的孙子类(A1的子类)： B
B的子类： B1
B的子孙类(B1的子类)： C

 */
public class Demo<T> {
    public void extendsTest(List<? extends T> list){
        System.out.println("extendsTest ok");
    }

    public void superTest(List<? super T> list){
        System.out.println("superTest ok");
    }

    public static void main(String[] args) {
        Demo<B> tt2 = new Demo<>();
        List<A> aList1 = new ArrayList<>();
        List<A1> a1List1 = new ArrayList<>();
        List<A2> a2List1 = new ArrayList<>();
        List<B> bList1 = new ArrayList<>();
        List<B1> b1List1 = new ArrayList<>();
        List<C> cList = new ArrayList<>();

        // extends 子类对象，使用继承基类的子类都能用
        // 可以理解成向下兼容
        // tt2 B类的对象
//        tt2.extendsTest(aList1);       // 基类的基类对象    报错
//        tt2.extendsTest(a1List1);      // 基类对象          报错
//        tt2.extendsTest(a2List1);      // 基类的兄弟类对象   报错
        tt2.extendsTest(bList1);       // 自己对象          可用
        tt2.extendsTest(b1List1);      // 子类对象          可用
        tt2.extendsTest(cList);        // 子类的子类         可用

        tt2.superTest(aList1);      // 基类的基类    可用
        tt2.superTest(a1List1);     // 基类          可用
//        tt2.superTest(a2List1);     // 基类的兄弟类   报错
        tt2.superTest(bList1);      // 自己对象     可用
//        tt2.superTest(b1List1);     // 子类对象     报错
//        tt2.superTest(cList);       // 子类的子类   报错
    }
}
