package com.java.i18n;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化相关API的使用
 */
public class Test {
    public static void main(String[] args) {
        testLocale();
        testDateFormat();
        testNumberFormat();
        testMessageFormat();
        testResourceBundle();
    }

    public static void testLocale(){
        // 中国地区
        Locale locale = Locale.CHINA;
        System.out.println(locale.getCountry());
        System.out.println(locale.getLanguage());
    }

    public static void testDateFormat(){
        Locale china = Locale.CHINA;
        Date date = new Date();
        // 创建DateFormat对象
        DateFormat dateFormat = DateFormat
                .getDateTimeInstance(DateFormat.FULL, DateFormat.DEFAULT, china);
        // 格式化日期
        String formatDate = dateFormat.format(date);

        System.out.println(formatDate);

        String time = "2020年6月12日 13:33:50";
        // 指定解析的格式与time字符串对应
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            // 解析字符串成日期
            Date parse = simpleDateFormat.parse(time);

            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void testNumberFormat(){
        Locale china = Locale.CHINA;
        double d = 12436.6523;
        // 指定国家的数字表示格式化
        NumberFormat numberFormat = NumberFormat.getNumberInstance(china);
        String format = numberFormat.format(d);
        System.out.println(format); //12,436.652

        // 指定国家的货币表示格式化
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        // 设置小数表示位数
        currencyFormat.setMaximumFractionDigits(4);
        String format1 = currencyFormat.format(d);
        System.out.println(format1); //￥12,436.6523

        String number = "213,123.54";
        String $number = "￥213,123.54";
        // 解析字符串成数字和货币表示形式
        double parse1 = 0, parse2 = 0;
        try {
            parse1 = (double)numberFormat.parse(number);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            parse2 = (double)currencyFormat.parse($number);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse1);
        System.out.println(parse2);
    }

    public static void testMessageFormat(){
        // 模式字符串(有占位符可填充),{}括号中可以是0-9数字
        String message = "今天吃了吗?{0}" +
                         "你搁这养生呢?{1}" +
                         "就这{2}";

        String format =                                  // 参数填充占位符
                MessageFormat.format(message, "吃饱了吗?", "你搁这吃屁呢?", "咋的");
        System.out.println(format);
    }

    public static void testResourceBundle(){
        //获取源捆绑
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n", Locale.JAPAN);
        String country = resourceBundle.getString("country");
        String language = resourceBundle.getString("language");
        System.out.println(country+" : "+language);
    }
}
