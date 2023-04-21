package com.wei.java.design.pattern.template.service.longsheng;


import java.util.Map;

/**
 * @author zhoulongsheng
 * @version $Id: IAtopAction.java, v 0.1 2020年6月4日 下午14:14:55 zhoulongsheng Exp $
 */
public interface IAtopAction<R> {

    /**
     * 执行前的参数处理
     * 若请求入参不合法，抛出ValidationException
     *
     * @throws ValidationException
     */
    void checkParams() throws ValidationException;

    /**
     * 权限校验
     * 若没有权限，抛出ValidationException
     *
     * @throws ValidationException
     */
    void checkPermission() throws ValidationException;

    /**
     * 初始化数据处理器
     *
     * @return
     * @throws ProdserviceBaseException
     */
    void initializeProcessors(Map<IProcEnum, Object> map) throws ProdserviceBaseException;

    /**
     * 数据处理
     * 将内部的数据结构，转化为外部数据结构VO
     *
     * @param actionResult
     * @return ViewResponse
     * @throws ProdserviceBaseException
     */
    R dataHandler(ActionResult actionResult) throws ProdserviceBaseException;
}
