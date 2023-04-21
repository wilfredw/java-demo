package com.wei.java.design.pattern.template.service.v3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author buhuan.wang
 * @since 2023/4/19
 */
public enum IpcHestiaErrorEnums {
    SYSTEM_ERROR(1, "系统异常", Arrays.asList()),
    BUSINESS_EXCEPTION(2, "业务异常，错误码：{0}，错误消息：{1}", Arrays.asList()),
    PARAM_ERROR(3, "参数错误", Arrays.asList()),
    ;


    private static Map<String, IpcHestiaErrorEnums> mappingErrorCodeToHestiaError = new HashMap<>();
    private static Map<Integer, IpcHestiaErrorEnums> errorCodeToHestiaError = new HashMap<>();

    static {
        for (IpcHestiaErrorEnums errorEnums : IpcHestiaErrorEnums.values()) {
            errorCodeToHestiaError.put(errorEnums.getCode(), errorEnums);
            for (String errorCode : errorEnums.getMappingErrorCodes()) {
                mappingErrorCodeToHestiaError.put(errorCode, errorEnums);
            }
        }
    }

    public static IpcHestiaErrorEnums getByCode(Integer code) {
        return errorCodeToHestiaError.get(code);
    }

    public static IpcHestiaErrorEnums getByMappingErrorCode(String codeStr) {
        return mappingErrorCodeToHestiaError.get(codeStr);
    }

    private int code;
    private String message;
    private List<String> mappingErrorCodes;

    IpcHestiaErrorEnums(int code, String message, List<String> mappingErrorCodes) {
        this.code = code;
        this.message = message;
        this.mappingErrorCodes = mappingErrorCodes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMappingErrorCodes() {
        return mappingErrorCodes;
    }

    public void setMappingErrorCodes(List<String> mappingErrorCodes) {
        this.mappingErrorCodes = mappingErrorCodes;
    }
}
