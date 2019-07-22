package com.wei.java.reflect.basereflect;

/**
 * Created by viruser on 2019/7/22.
 */
public class ChildPO {

    private String id;

    @UpdateKey
    private String code;

    @UpdateKey
    private String type;

    private Integer value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
