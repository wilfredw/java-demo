package com.wei.java.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Java 8 日期时间
 *
 * @author buhuan.wang
 * @since 2021/3/21
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        //获取秒数 gmt+8
        Long second8 = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        //获取毫秒数 gmt+8
        Long milliSecond8 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //获取秒数 gmt+0
        Long second0 = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+0"));
        //获取毫秒数 gmt+0
        Long milliSecond0 = LocalDateTime.now().toInstant(ZoneOffset.of("+0")).toEpochMilli();
        //获取秒数 currentTimeMillis
        long totalMilliSeconds = System.currentTimeMillis();

        System.out.println("+8 second:      " + second8);
        System.out.println("+8 milliSecond: " + milliSecond8);

        System.out.println("+0 seconds:     " + second0);
        System.out.println("+0 milliSeconds:" + milliSecond0);

        //显示时间
        System.out.println("+s seconds:     " + totalMilliSeconds);

        // 偏移时间
        System.out.println("offset milliSeconds: " + String.valueOf(milliSecond8 - milliSecond0));
        // 偏移时间
        System.out.println("offset minutes: " + String.valueOf((milliSecond8 - milliSecond0) / 1000 / 60));
        System.out.println("offset minutes: " + String.valueOf(getTimeZoneOffset()));
        System.out.println("offset ms:   " + String.valueOf(getTimeZoneOffsetInMS()));

    }

    //东八区--->+08:00，获取的格式类似于“+08:00”，不会出现“CET”这种某些地区“标准时间”的格式
    public static String getTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        return "" + timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5);
    }

    //东八区--->480
    public static int getTimeZoneOffset() {
        Calendar calendar = new GregorianCalendar();
        return calendar.getTimeZone().getOffset(System.currentTimeMillis()) / 1000 / 60;
    }

    public static int getTimeZoneOffsetInMS() {
        Calendar calendar = new GregorianCalendar();
        return calendar.getTimeZone().getOffset(System.currentTimeMillis());
    }
}
