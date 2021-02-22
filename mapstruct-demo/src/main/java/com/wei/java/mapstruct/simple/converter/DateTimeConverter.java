package com.wei.java.mapstruct.simple.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/2/22
 */
public class DateTimeConverter {
    public static LocalDateTime strToDate(String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse("2018-01-12 17:07:05", df);
    }
}
