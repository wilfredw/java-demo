package com.wei.java.design.pattern.template.service.longsheng.actionengine;

import com.wei.java.design.pattern.template.service.longsheng.actionengine.enums.IProcEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoulongsheng
 * @version $Id: ActionResult.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 **/
@Data
public class ActionResult {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 消息提示
     */
    private String message;

    /**
     * 业务结果
     */
    private final Map<IProcEnum, Object> processorResults = new HashMap<>();

    /**
     * 添加数据
     *
     * @param key   数据key
     * @param value 数据值
     */
    public void addProcessorResult(IProcEnum key, Object value) {
        if (null != value) {
            this.processorResults.put(key, value);
        }
    }

    /**
     * 获取数据
     *
     * @param key 数据key
     */
    public Object getProcessorResult(IProcEnum key) {
        return this.processorResults.get(key);
    }

    /**
     * 获取数据
     *
     * @param key 数据key
     */
    public <T> T getProcessorResult(IProcEnum key, Class<T> cls) {
        return (T) this.processorResults.get(key);
    }

}
