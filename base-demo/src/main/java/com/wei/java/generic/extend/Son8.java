package com.wei.java.generic.extend;

// 父类和子类的泛型参数具有关系
class Son8<T, E extends T> extends Father<T> {

    E otherData;

    public Son8(T data, E otherData) {
        super(data);
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "Son8 [otherData=" + otherData + ", data=" + data + "]";
    }

}
