package com.wei.java.design.pattern.template.service.longsheng;


/**
 * Atop服务实现模板接口
 *
 * @author zhoulongsheng
 * @version $Id: IAtopTemplate.java, v 0.1 2020年6月9日 下午11:14:55 zhoulongsheng Exp $
 */
public interface IAtopTemplate {

    /**
     * Atop服务调用的接口模板
     *
     * @param action
     * @return
     */
    <R, P> AtopResult<R> execute(AbstractAtopAction<R, P> action);

}
