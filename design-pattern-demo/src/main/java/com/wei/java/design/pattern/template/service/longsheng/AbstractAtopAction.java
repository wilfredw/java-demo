package com.wei.java.design.pattern.template.service.longsheng;

/**
 * Atop接口的抽象模板实现类
 *
 * @param <P> Atop接口入参数据类型
 * @param <R> Atop接口出参数据类型
 * @author zhoulongsheng
 * @version $Id: AbstractAtopAction.java, v 0.1 2020年6月9日 下午11:14:55 zhoulongsheng Exp $
 */
public abstract class AbstractAtopAction<R, P> extends AbstractAction<R, P> implements IAtopAction<R> {

    private ApiRequestDO apiRequestDO;


    public AbstractAtopAction(String serviceName, P param) {
        super(serviceName, param);
    }

    public AbstractAtopAction(String serviceName, P param, ApiRequestDO apiRequestDO) {
        super(serviceName, param);
        this.apiRequestDO = apiRequestDO;
    }


    public ApiRequestDO getApiRequestDO() {
        return apiRequestDO;
    }

}
