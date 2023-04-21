package com.wei.java.design.pattern.template.service.longsheng;

/**
 * 业务活动上下文管理器。
 *
 * @author zhoulongsheng
 * @version $Id: RuntimeContextHolder.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
public class RuntimeContextHolder {
    /**
     * 上下文信息
     */
    private static final ThreadLocal<RuntimeContext> CURRENT_RUNTIME_CONTEXT = new ThreadLocal<RuntimeContext>();

    /**
     * 获取当前线程下的上下文信息.
     *
     * @return 上下文信息
     */
    public static RuntimeContext currentRuntimeContext() {
        return CURRENT_RUNTIME_CONTEXT.get();
    }

    /**
     * 设置当前线程下的上下文信息
     *
     * @param runtimeContext 上下文信息
     */
    public static void setCurrentRuntimeContext(RuntimeContext runtimeContext) {
        CURRENT_RUNTIME_CONTEXT.set(runtimeContext);
    }

    /**
     * 清空当前线程下的上下文信息
     */
    public static void clear() {
        CURRENT_RUNTIME_CONTEXT.remove();
    }
}
