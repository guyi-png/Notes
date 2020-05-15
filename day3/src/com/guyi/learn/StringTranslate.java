package com.guyi.learn;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * String 与 其他类的转化
 */
public class StringTranslate {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "123";
        int num = Integer.parseInt(str);  //将字符串转化为int类型，自动装箱
        System.out.println(num);

        int num1 = 888;
        String str1 = null;
        str1 = String.valueOf(num1);    //将int类型转化为字符串类型
        System.out.println(str1);

        String str2 = "奥里给";
        char[] chars = str2.toCharArray(); //将字符串转化为char[]数组
        System.out.println(chars.length);

        String str3 = new String(chars); // 将char[]数组转为字符串
        System.out.println(str3);

        String str4 = "32156奥里给";
        byte[] bytes = str4.getBytes("gbk"); //将字符串转化为byte[]数组
        System.out.println(Arrays.toString(bytes));

        String str5 = new String(bytes);  //将byte[]数组转化为字符串
        System.out.println(str5);  //上面的bytes数组的编码为“gbk”,而此时是用默认的编码
        String str6 = new String(bytes,"gbk");//现在指定编码集
        System.out.println(str6);
    }

}
