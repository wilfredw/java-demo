package com.wei.java.lambda;

@FunctionalInterface
public interface PersonFunction<T, R, S> {
    R apply(T t, S s);
}
