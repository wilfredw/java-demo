package com.wei.java.design.pattern.template.service.v3;

import java.text.MessageFormat;

/**
 * @author buhuan.wang
 * @since 2023/4/19
 */
public class IpcAbsResponseUtils {
    public static AbsResponse getResp(IpcHestiaErrorEnums errorEnums, Object[] params) {
        return new AbsResponse(errorEnums.getCode(), MessageFormat.format(errorEnums.getMessage(), params));
    }

    public static AbsResponse getResp(IpcHestiaErrorEnums errorEnums) {
        return new AbsResponse(errorEnums.getCode(), errorEnums.getMessage());
    }
}
