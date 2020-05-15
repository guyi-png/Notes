package com.Date.learn;

import java.util.Date;

/**
 * jdk 8.0 之前的日期和时间的API
 * java.util.Date类
 *      |--java.sql.Date类
 */
public class DateTimeTest {
    public static void main(String[] args) {
        long time = System.currentTimeMillis(); //返回从1970年1月1日0:0:00到现在的毫秒数
        System.out.println(time);
        test();
    }

    public static void test(){
        Date date1 = new Date();
        System.out.println(date1.toString()); //Thu May 14 16:53:04 CST 2020
        System.out.println(date1.getTime());  //返回从1970年1月1日0:0:00到现在的毫秒数

        Date date2 = new Date(1558447221349L);
        System.out.println(date2.toString());  //Tue May 21 22:00:21 CST 2019

        java.sql.Date date3 = new java.sql.Date(1555447221341L);
        System.out.println(date3.toString());//2019-04-17

        //java.util.Date转为java.sql.Date的方法
        Date date4 = new Date();
        java.sql.Date date5 = new java.sql.Date(date4.getTime());
        System.out.println();
    }
}
