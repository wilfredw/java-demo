package com.wei.java.annotation;

import java.lang.annotation.*;

/**
 * Created by viruser on 2019/6/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
@interface MyAnnotation {

    String   value() default "";
    String   name();
    int      age() default 0;
    String[] newNames() default {};
}

