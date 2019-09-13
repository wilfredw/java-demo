package com.wei.java.reflect.basereflect;

import java.lang.annotation.*;

/**
 * Created by viruser on 2019/7/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface UpdateKey {
    public String name() default "default";
}
