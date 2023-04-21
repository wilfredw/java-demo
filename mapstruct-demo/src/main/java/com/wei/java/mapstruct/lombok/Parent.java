package com.wei.java.mapstruct.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author buhuan.wang
 * @SuperBuilder 和@Builder在父类和子类中不能混用
 * @SuperBuilder在所有的父类和子类中都必须使用上，缺一不可。
 * @since 2023/4/21
 */
@Getter
@ToString
// @SuperBuilder
public class Parent {

    private long id;

    private String name;

    public Parent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //    @Builder(builderMethodName = "childBuilder")
    @Getter
    @ToString
    // @SuperBuilder
    public static class Child extends Parent {
        private String value;

        @Builder(builderMethodName = "fullChildBuilder")
        public Child(String value, Long id, String name) {
            super(id, name);
            this.value = value;
        }
    }


}
