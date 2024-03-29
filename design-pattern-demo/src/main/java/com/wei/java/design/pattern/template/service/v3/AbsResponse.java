package com.wei.java.design.pattern.template.service.v3;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by xiejun on 17/12/5.
 */
public class AbsResponse<T> implements Serializable {
    private static final long serialVersionUID = 5014379068811962022L;

    /**
     * 错误代码：如果为0则表示成功，其他均为失败，值自定义
     */
    private int code;

    private String tid;

    /**
     * 错误信息
     */
    private String msg;

    private String body; // 结果信息

    private T data; // 返回结果

    /**
     * 参数列表
     */
    @JsonIgnore
    private Map<String, String> params;


    public AbsResponse(T data) {
        this(0, null, null, data);
    }

    public AbsResponse() {
        this(0, null);
    }

    public AbsResponse(int code, String msg) {
        this(code, msg, null);
    }

    public AbsResponse(int code, String msg, String body) {
        this(code, msg, body, null);
    }

    public AbsResponse(int code, String msg, String body, T data) {
        this.code = code;
        this.msg = msg;
        this.body = body;
        this.data = data;
    }

    public AbsResponse<T> setResult(int code, String msg) {
        this.code = code;
        this.msg = msg;

        return this;
    }

    public static AbsResponse setSuccess(String msg) {

        AbsResponse absResponse = new AbsResponse(0, msg);
        return absResponse;
    }


    public AbsResponse<T> setResult(int code, String msg,
                                    String body, T data) {
        this.code = code;
        this.msg = msg;
        this.body = body;
        this.data = data;
        return this;
    }

    /**
     * 由另一个对象赋值，可能泛型不一致
     *
     * @param ano
     * @return
     */
    public AbsResponse<T> setResult(AbsResponse<?> ano) {
        return setResult(ano.getCode(), ano.getMsg(), ano.getBody(), null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "AbsResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", params=" + params +
                '}';
    }
}
