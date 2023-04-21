package com.wei.java.mapstruct.lombok;

/**
 * @author buhuan.wang
 * @since 2023/4/21
 */
public class TestDemo {
    public static void main(String[] args) {
//        Parent.Child child = Parent.Child.childBuilder().value("1").build();
//        System.out.println(child);

        Parent.Child child2 = Parent.Child.fullChildBuilder().value("1").id(1L).name("aa").build();
        System.out.println(child2);
    }
}
