package com.Date.learn;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * jdk8.0中的日期时间API
 */
public class DateTimeTest2 {
    public static void main(String[] args) {
        localDateTimeTest();
        instantTest();
        dateTimeFormatter();
    }

    public static void localDateTimeTest(){
        LocalDate localDate = LocalDate.now();//2020-05-15
        LocalTime localTime = LocalTime.now();//09:20:49.868971800
        LocalDateTime localDateTime = LocalDateTime.now();//2020-05-15T09:20:49.868971800

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020,11,22,12,52,26,4314123);
        System.out.println(localDateTime1);

        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getMinute());
        System.out.println(localDateTime1.getYear());

        //不可变性
        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(23);
        System.out.println(localDateTime1);  //2020-11-22T12:52:26.004314123
        System.out.println(localDateTime2);  //2020-11-23T12:52:26.004314123

        LocalDateTime localDateTime3 = localDateTime1.withHour(22); //2020-11-22T22:52:26.004314123
        LocalDateTime localDateTime4 = localDateTime1.plusMonths(3);//2021-02-22T12:52:26.004314123
        LocalDateTime localDateTime5 = localDateTime1.minusMonths(3);//2020-08-22T12:52:26.004314123
        System.out.println(localDateTime5);
    }

    public static void instantTest(){
        Instant instant = Instant.now(); //2020-05-15T02:55:36.606534900Z（本初子午线为时间标准） 在中国有8小时差异，
        System.out.println(instant);

        OffsetDateTime offsetDateTime  =instant.atOffset(ZoneOffset.ofHours(8)); //添加偏移量
        System.out.println(offsetDateTime);//2020-05-15T11:18:46.897640400+08:00

        long milli = instant.toEpochMilli();//获取自1970年1月1日0:0:00到此瞬间的毫秒数
        System.out.println(milli);
        Instant instant1 = Instant.ofEpochMilli(1589513020603L);//将毫秒数转成瞬间值
        System.out.println(instant1);
    }

    public static void dateTimeFormatter(){
        //DateTimeFormatter: 格式化或解析日期，时间
        //方式一：预定义的标准格式
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String string = formatter.format(localDateTime);

        System.out.println(string); //2020-05-15T12:02:53.4683808
        TemporalAccessor temporalAccessor = formatter.parse("2022-05-15T12:33:53.4683808");
        System.out.println(temporalAccessor);



        //方式二：本地化相关的格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
//        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);  ???
        String string2 = dateTimeFormatter.format(localDateTime);
        System.out.println(string2);    //2020/5/15 下午12:23

        //方式三：自定义的格式
        DateTimeFormatter dateTimeFormatter1= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String str = dateTimeFormatter1.format(LocalDateTime.now());
        System.out.println(str);
    }
}
