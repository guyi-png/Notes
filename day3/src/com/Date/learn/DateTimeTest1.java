package com.Date.learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk8.0之前
 * SimpleDateFormat类:对日期与Date类的格式化和解析
 * 1.格式化： 日期 --> 字符串
 * 2.解析: 格式化的逆过程： 字符串 --> 日期
 * Calendar
 */
public class DateTimeTest1 {
    public static void main(String[] args) {
            testSimpleDateFormat();
            testExer();
            testCalendar();
    }

    public static void testSimpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date = new Date();
        String format = sdf.format(date);  // 日期 --> 字符串
        System.out.println(format);

        String str = "2020/10/08 上午11:00";  //字符串 --> 日期
        try{
            Date date1 = sdf.parse(str);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
// ------------------------------------------------------------------------
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format1 = sdf1.format(date);
        System.out.println(format1);  //2020/05/14 22:17:57
        try{
            Date date2 = sdf1.parse("2020/11/25 12:22:34");
            System.out.println(date2);
        }catch(ParseException e){
            e.getMessage();
        }
    }

    //将字符串“2020/09/08”转化为java.sql.Date
    public static void testExer(){
        String birth = "2020/09/08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            Date date = sdf.parse(birth);
            System.out.println(date);
            java.sql.Date birthDate = new java.sql.Date(date.getTime());
            System.out.println(birthDate);
        }catch(ParseException e){
            e.getMessage();
        }
    }

    public static void testCalendar(){
        //1.Calendar 是抽象类
        //实例化的方式： 1.通过子类GregorianCalendar 2.调用静态方法getInstance()
        Calendar calendar = Calendar.getInstance();

        int days = calendar.get(Calendar.DAY_OF_MONTH); //get(),获取当月里的第几天
        System.out.println(days);

        calendar.set(Calendar.DAY_OF_MONTH,22);  //set(),将当月的第几天设为指定的第几天
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        calendar.add(Calendar.DAY_OF_MONTH,3);  //add(),将当月的第几天加指定的天数
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        Date date = calendar.getTime();  //获取1970-1-1 0:0:00毫秒数
        System.out.println(date);

        Date date1 = new Date();
        calendar.setTime(date1);     //将Date类的对象的毫秒数设为Calendar类的对象的日历
    }
}
