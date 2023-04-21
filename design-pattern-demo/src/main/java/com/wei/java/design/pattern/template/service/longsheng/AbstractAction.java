package com.wei.java.design.pattern.template.service.longsheng;


/**
 * 抽象模板实现类
 *
 * @author zhoulongsheng
 * @version $Id: AbstractAtopAction.java, v 0.1 2020年6月9日 下午11:14:55 zhoulongsheng Exp $
 */
public abstract class AbstractAction<R, P> implements IActionHandler {

    private String serviceName;
    private P parameter;

    public AbstractAction(String serviceName, P parameter) {
        this.serviceName = serviceName;
        this.parameter = parameter;
    }

    public String getServiceName() {
        return serviceName;
    }

    public P getParameter() {
        return parameter;
    }

    /**
     * 执行成功时的处理逻辑
     */
    @Override
    public void onSuccess() {
    }

    /**
     * 执行失败时的处理逻辑
     */
    @Override
    public void onError() {
    }

    /**
     * 执行异常时的处理逻辑
     */
    @Override
    public void onException() {
    }

    /**
     * 执行结束（不论成功、失败还是异常等）必须要处理的一些逻辑
     */
    @Override
    public void onFinally() {
    }
}
