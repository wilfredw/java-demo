package com.wei.java.generic.extend;


class Son2<E, F> extends Father<F> {// 子类增加了一个泛型参数，父类的泛型参数不能遗漏，所以仍然要定义

    E otherData;

    public Son2(F data, E otherData) {
        super(data);
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "Son2 [otherData=" + otherData + ", data=" + data + "]";
    }

}
