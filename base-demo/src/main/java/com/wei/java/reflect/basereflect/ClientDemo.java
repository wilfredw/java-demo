package com.wei.java.reflect.basereflect;

/**
 * Created by viruser on 2019/7/22.
 */
public class ClientDemo{
    public static void main(String[] args) {
        ChildDao childDao = new ChildDao();
        ChildPO childPO = new ChildPO();
        childPO.setId("123");
        childPO.setCode("A001");
        childPO.setValue(101);
        childDao.update(childPO);
    }
}
