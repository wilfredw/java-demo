package com.wei.java.utils.validation.validator.enums;

/**
 * @author buhuan.wang
 * @since 2022/2/11
 */
public enum TypeEnum {
    NORMAL("normal"),
    SPECIAL("special");
    private String code;

    private TypeEnum(String code) {
        this.code = code;
    }
}
