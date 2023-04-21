package com.wei.java.design.pattern.template.service.v2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wei.java.design.pattern.template.service.v2.ApiErrorConstants.API_ERROR_CODE_START;

/**
 * @author buhuan.wang
 * @since 2023/3/16
 */
public enum ApiErrorEnum {

    SYSTEM_ERROR(API_ERROR_CODE_START + 1, "system error.", Arrays.asList()),
    PARAM_NULL(API_ERROR_CODE_START + 2, "param is null.", Arrays.asList()),
    PARAM_ERROR(API_ERROR_CODE_START + 3, "param error.", Arrays.asList()),

    USER_NOT_EXIST(API_ERROR_CODE_START + 10, "user not exist.", Arrays.asList("NOT_EXISTS")),
    USERNAME_PASSWORD_WRONG(API_ERROR_CODE_START + 11, "user name or password is wrong.",
            Arrays.asList("")),
    ;

    private static Map<String, ApiErrorEnum> errorCodeMap;
    private static Map<String, ApiErrorEnum> mappingErrorCodeMap;

    private Integer errorCode;
    private String errorMsg;
    private List<String> mappingErrorCodes;

    ApiErrorEnum(Integer errorCode, String errorMsg, List<String> mappingErrorCodes) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.mappingErrorCodes = mappingErrorCodes;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorCodeStr() {
        return String.valueOf(errorCode);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    static {
        errorCodeMap = new HashMap<>();
        mappingErrorCodeMap = new HashMap<>();
        for (ApiErrorEnum apiErrorEnum : ApiErrorEnum.values()) {
            errorCodeMap.put(apiErrorEnum.getErrorCodeStr(), apiErrorEnum);
            for (String mappingErrorCode : apiErrorEnum.mappingErrorCodes) {
                mappingErrorCodeMap.put(mappingErrorCode, apiErrorEnum);
            }
        }
    }

    public static ApiErrorEnum getByErrorCode(String errorCode) {
        return errorCodeMap.get(errorCode);
    }

    public static ApiErrorEnum getByMappingErrorCode(String mappingErrorCode) {
        return mappingErrorCodeMap.get(mappingErrorCode);
    }
}
