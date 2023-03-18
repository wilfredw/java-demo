package com.wei.java.excel.easyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private String date;
    @ExcelProperty("数字标题")
    private String doubleData;
    @ExcelProperty("value4")
    private String value4;
    @ExcelProperty("value5")
    private String value5;
    @ExcelProperty("value6")
    private String value6;
    @ExcelProperty("value7")
    private String value7;
    @ExcelProperty("value8")
    private String value8;
    @ExcelProperty("value9")
    private String value9;
    @ExcelProperty("value10")
    private String value10;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}