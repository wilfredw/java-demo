package com.wei.java.generic.extend;

class Father<T> {
    T data;

    public Father(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Father [data=" + data + "]";
    }

}
