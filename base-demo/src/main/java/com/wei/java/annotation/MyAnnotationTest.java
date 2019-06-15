package com.wei.java.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by viruser on 2019/6/15.
 */
public class MyAnnotationTest {

    public static void main(String[] args) {
        //通过反射获得MyClass的注解信息
        try {
            Class<?> myAnnotationClass = Class.forName("com.wei.java.annotation.MyClass");

            MyAnnotation cAnnotation = myAnnotationClass.getAnnotation(MyAnnotation.class);
            System.out.println(cAnnotation.name());
            System.out.println(cAnnotation.value());

            Field field = null;
            try {
                field = myAnnotationClass.getDeclaredField("data");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }


            Annotation annotation = field.getAnnotation(MyAnnotation.class);
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }

            Method method = null;
            try {
                method = myAnnotationClass.getMethod("go", String.class);
                annotation = method.getAnnotation(MyAnnotation.class);
                if (annotation != null) {
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("name: " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Class[] parameterTypes = method.getParameterTypes();

            int i = 0;
            for (Annotation[] annotations : parameterAnnotations) {
                Class parameterType = parameterTypes[i++];

                for (Annotation aaa : annotations) {
                    if (aaa instanceof MyAnnotation) {
                        MyAnnotation myAnnotation = (MyAnnotation) aaa;
                        System.out.println("param: " + parameterType.getName());
                        System.out.println("name : " + myAnnotation.name());
                        System.out.println("value: " + myAnnotation.value());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
