package com.wei.java.design.pattern.template.service.longsheng;

/**
 * 类的详细说明
 * 如果继承某个接口{@link StateContext}，提供一个链接，方便使用
 * <p>
 * 如果是过期的接口，那么请尽量提供用于替换的类的链接地址{@link StateContext}
 *
 * @param <S> 如果用到泛型，可以这样注释
 * @param <E> 如果用到泛型，可以这样注释
 * @author buhuan.wang
 * @since 2023/4/19
 */
public class ApiRequestDO {

    private String apiName;
    private String apiVersion;
    private String clientId;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApi() {
        return null;
    }
}
