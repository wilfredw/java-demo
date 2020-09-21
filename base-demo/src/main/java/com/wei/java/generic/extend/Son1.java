package com.wei.java.generic.extend;

class Son1<T> extends Father<T> {// 最正常的继承，子类的泛型参数和父类的参数是一致的

    public Son1(T data) {
        super(data);
    }

    @Override
    public String toString() {
        return "Son1 [data=" + data + "]";
    }

}
