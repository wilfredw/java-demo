package com.wei.java.mapstruct.simple.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/2/22
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    Java("000", "Java开发工程师"),
    DB("001", "数据库管理员"),
    LINUX("002", "Linux运维员");

    private String value;
    private String title;

}