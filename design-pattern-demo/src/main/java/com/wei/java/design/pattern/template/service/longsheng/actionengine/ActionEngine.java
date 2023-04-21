package com.wei.java.design.pattern.template.service.longsheng.actionengine;

import com.wei.java.design.pattern.template.service.longsheng.actionengine.enums.IProcEnum;

import java.util.Map;

/**
 * action 分发的内部服务.
 *
 * <li>action分发，指定处理器处理
 * <li>结果处理 (封装成内部处理结果模型)
 * <li>内部结果转换返回
 *
 * @author zhoulongsheng
 * @version $Id: ActionEngine.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
public interface ActionEngine {

    /**
     * 分发服务处理(所有action入参，隐式变成)
     *
     * @param map 执行的MAP
     * @return 执行结果
     */
    ActionResult executeAction(Map<IProcEnum, Object> map);
}