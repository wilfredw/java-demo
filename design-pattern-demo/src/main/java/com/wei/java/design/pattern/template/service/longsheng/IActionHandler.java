package com.wei.java.design.pattern.template.service.longsheng;

/**
 * 行为执行处理
 *
 * @author zhoulongsheng
 * @version $Id: IActionHandler.java, v 0.1 2020年6月4日 下午14:14:55 zhoulongsheng Exp $
 */
public interface IActionHandler {
    /**
     * 执行成功时的处理逻辑
     */
    void onSuccess();

    /**
     * 执行失败时的处理逻辑
     */
    void onError();

    /**
     * 执行异常时的处理逻辑
     */
    void onException();

    /**
     * 执行结束（不论成功、失败还是异常等）必须要处理的一些逻辑
     */
    void onFinally();
}
