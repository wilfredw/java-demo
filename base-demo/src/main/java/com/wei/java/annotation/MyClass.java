package com.wei.java.annotation;

/**
 * Created by viruser on 2019/6/15.
 */
@MyAnnotation(
        name="Jakob",
        age=37,
        newNames={"Jenkov", "Peterson"}
)
public class MyClass {
    @MyAnnotation(name = "field")
    private String data;

    @MyAnnotation(name = "go")
    public void go(@MyAnnotation(name="param") String param) {
        System.out.println(data);
    }
}

