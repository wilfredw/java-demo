package com.wei.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by viruser on 2019/9/7.
 */
public class DateTimeUtil {
    public static String nowSimpleFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return formatter.format(new Date());
    }
}
