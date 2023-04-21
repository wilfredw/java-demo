package com.wei.java.design.pattern.template.service.v3;

/**
 * @author buhuan.wang
 * @since 2023/4/19
 */
public interface IpcHandler<P, R> {

    AbsResponse<R> handle(P param);

}
