package com.wei.java.design.pattern.template.service.v2;

/**
 * @author zhoulongsheng
 * @version $Id: IAtopAction.java, v 0.1 2020年6月4日 下午14:14:55 zhoulongsheng Exp $
 */
public interface IAtopAction<R, P> {

    /**
     * 执行前的参数处理
     * 若请求入参不合法，抛出ValidationException
     *
     * @throws
     */
    void checkParams(P p);


    /**
     * 逻辑执行
     *
     * @return
     * @throws
     */
    R doAction(P p);
}
