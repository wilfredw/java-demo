package com.wei.java.utils.validation.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/7/13
 */
public class DemoCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String msgType;

    @NotNull
    @Size(min = 1, max = 5)
    private String name;

    @NotNull
    @Size(min = 1, max = 3)
    private List<String> ids;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
