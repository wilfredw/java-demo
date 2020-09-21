package com.wei.java.generic.extend;

public class Son9 {
}

// 下面的写法也是错误的，要是父类的T为Object 这时E为什么呢？
// class Son9<E, E super T> extends Father<T> {
//
// public Son9(T data) {
// super(data);
// }
// }
