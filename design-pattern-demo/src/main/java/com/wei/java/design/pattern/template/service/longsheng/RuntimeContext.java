package com.wei.java.design.pattern.template.service.longsheng;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 运行上下文，可以通过线程变量传递的上下文信息
 *
 * @author zhoulongsheng
 * @version $Id: RuntimeContext.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
public class RuntimeContext {

    protected static final Logger logger = Logger.getLogger(RuntimeContext.class);

    /**
     * 入参Json对象
     */
    private Map<String, Object> requestJsonMaps;

    /**
     * 获取Web层请求入参
     *
     * @param requestParameterName
     * @return
     */
    public Object getRequestParameter(String requestParameterName) {
        if (requestJsonMaps == null || requestJsonMaps.isEmpty()) {
            return null;
        }
        return requestJsonMaps.get(requestParameterName);
    }

    /**
     * Setter method for property <tt>requestJsonMaps</tt>
     *
     * @param requestJsonMaps value to be assigned to property requestJsonMaps
     */
    public void setRequestJsonMaps(Map<String, Object> requestJsonMaps) {
        this.requestJsonMaps = requestJsonMaps;
    }

    /**
     * 获取可操作的请求对象
     *
     * @return
     */
    public Map<String, Object> getRequestOptMaps() {
        return requestJsonMaps;
    }

    /**
     * Getter method for property <tt>requestJsonMaps</tt>.
     *
     * @return property value of requestJsonMaps
     */
    public Map<String, Object> getRequestJsonMaps() {
        return Collections.unmodifiableMap(requestJsonMaps);
    }
}
