package com.wei.java.generic.extend;

// 父类指定了类型，子类又增加了，这时子类的只是新增加的泛型参数，跟父类没有关系
class Son5<T> extends Father<Integer> {
    T otherData;

    public Son5(Integer data, T otherData) {
        super(data);
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "Son5 [otherData=" + otherData + ", data=" + data + "]";
    }

}
