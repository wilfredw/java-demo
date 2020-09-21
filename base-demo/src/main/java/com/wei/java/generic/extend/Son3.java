package com.wei.java.generic.extend;


class Son3 extends Father {// 继承时不指定父类的泛型参数,会有警告信息：Father is a raw type.
    // References to generic type Father<T> should be
    // parameterized

    public Son3(Object data) {// 这个的data类型为Object
        super(data);
    }

    @Override
    public String toString() {
        return "Son3 [data=" + data + "]";
    }

}
