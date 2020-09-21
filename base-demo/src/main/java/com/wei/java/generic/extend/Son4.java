package com.wei.java.generic.extend;


// 继承时指定父类的泛型参数，子类就不用再写泛型参数，如果写了，那就是子类自己新增加的
class Son4 extends Father<Integer> {

    public Son4(Integer data) {
        super(data);
    }

    @Override
    public String toString() {
        return "Son4 [data=" + data + "]";
    }

}
