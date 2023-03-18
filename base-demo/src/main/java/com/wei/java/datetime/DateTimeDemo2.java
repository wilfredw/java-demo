package com.wei.java.datetime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
public class DateTimeDemo2 {
    public static void main(String[] args) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        System.out.println("currentLocalDateTime: " + currentLocalDateTime);

        //获取秒数 currentTimeMillis
        // 这是标准时区的时间戳
        long currentTimeMillis = System.currentTimeMillis();
        //显示时间
        System.out.println("currentTimeMillis:   " + currentTimeMillis);// 这是标准时区的时间戳

        Instant nowInstant = Instant.now();
        System.out.println("nowInstant: " + nowInstant);
        System.out.println("nowInstant getEpochSecond: " + nowInstant.getEpochSecond());
        System.out.println("nowInstant getNano       : " + nowInstant.getNano());

        ZoneOffset zoneOffset8 = ZoneOffset.of("+08:00");
        LocalDateTime dateTime8 = LocalDateTime.ofInstant(nowInstant, zoneOffset8);
        System.out.println("dateTime8: " + dateTime8);
        String strDate8 = dateTime8.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));   // 2017-01-05
        System.out.println("strDate8:" + strDate8);
        ZoneOffset zoneOffset0 = ZoneOffset.of("+00:00");
        LocalDateTime dateTime0 = LocalDateTime.ofInstant(nowInstant, zoneOffset0);
        System.out.println("dateTime0: " + dateTime0);
        String strDate0 = dateTime0.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));   // 2017-01-05
        System.out.println("strDate0:" + strDate0);

        //获取秒数 gmt+8
        // 当前地区的时间，以当前时区转换时间戳，这是标准时区的时间戳
        Long second8 = currentLocalDateTime.toEpochSecond(ZoneOffset.of("+8"));
        //获取毫秒数 gmt+8
        // 当前地区的时间，以当前时区转换时间戳，这是标准时区的时间戳
        Long milliSecond8 = currentLocalDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //获取秒数 gmt+0
        // 当前地区的时间，以GMT+0时区转换时间戳，快了8小时
        Long second0 = currentLocalDateTime.toEpochSecond(ZoneOffset.of("+0"));
        //获取毫秒数 gmt+0
        // 当前地区的时间，以GMT+0时区转换时间戳，快了8小时
        Long milliSecond0 = currentLocalDateTime.toInstant(ZoneOffset.of("+0")).toEpochMilli();

        System.out.println("+8 second:      " + second8);// 这是标准时区的时间戳
        System.out.println("+8 milliSecond: " + milliSecond8);// 这是标准时区的时间戳

        System.out.println("+0 seconds:     " + second0);// 这是+8时区的时间戳
        System.out.println("+0 milliSeconds:" + milliSecond0);// 这是+8时区的时间戳


        // 偏移时间，当前地区的时间，以GMT+0时区转换时间戳，快了8小时 
        System.out.println("offset milliSeconds: " + String.valueOf(milliSecond8 - milliSecond0));
        // 偏移时间，当前地区的时间，以GMT+0时区转换时间戳，快了8小时 
        System.out.println("offset minutes: " + String.valueOf((milliSecond8 - milliSecond0) / 1000 / 60));


        System.out.println("offset minutes: " + String.valueOf(getTimeZoneOffset()));
        System.out.println("offset ms:   " + String.valueOf(getTimeZoneOffsetInMS()));

        System.out.println("=====================");
        LocalTime localTime = currentLocalDateTime.toLocalTime();
        System.out.println("SecondOfDay: " + localTime.toSecondOfDay());

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
