package com.wei.java.reflect.basereflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by viruser on 2019/7/22.
 */
public class BaseDao<T> {
    void update(T var) {
        Class<?> varClass = var.getClass();
        Field[] fields = varClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
            f.setAccessible(true);
            Object value = null;
            try {
                value = f.get(var);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("field name: " + f.getName());
            System.out.println("field value: " + value);
            Annotation fieldAnnot = f.getAnnotation(UpdateKey.class);
            printAnnotation("annotations: ",fieldAnnot);
            if (null != fieldAnnot && null != value) {
                boolean hasUpdateKey = true;
                System.out.println("add update key: " + f.getName() + value);
            }
            if (null != value) {
                System.out.println("update for: " + f.getName() + value);
            }
            System.out.println();
        }

    }
    private static void printAnnotation(String msg,Annotation... annotations){
        System.out.println(">>>>>>>>>>>>>>>>>> "+msg+" ======================");
        if(annotations == null){
            System.out.println("Annotation is null");
        }
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println("<<<<<<<<<<<<<<<<<< "+msg+" ======================");
    }
}
