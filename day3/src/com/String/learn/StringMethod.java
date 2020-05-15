package com.String.learn;

/**
 *  String类中的常用方法
 */
public class StringMethod {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        String s = "HelloWorld";

        System.out.println(s.length());   // 返回字符串的长度

        System.out.println(s.charAt(9));  // 可以理解为数值的索引即 "[]"

        System.out.println(s.isEmpty());  // 判断是否为空

        String s1 = s.toUpperCase();    //字符串的元素全部传换为大写
        System.out.println(s1);

        String s3 = "             hello world             ";
        System.out.println(s3.trim());  //消除字符串前后的空格

        System.out.println(s.equals(s1));  //false 比较内容相同
        System.out.println(s.equalsIgnoreCase(s1)); //true 比较内容相同,忽略大小写

        String s4 = "haha";
        System.out.println(s.concat(s4));   //连接两个字符串，等价于"+"

        String s5 = "abc";
        String s6 = "abe";
        System.out.println(s5.compareTo(s6));  //-2，比较两个字符串的大小

        String s7 = "没毛病吧，干就完了，奥里给";
        String s8 = s7.substring(10,13);  //从s7中截取字符串,参数一开始处，参数二结束处，返回给s8
        System.out.println(s8);

        String s9 = "hello world";
        System.out.println(s9.endsWith("ld"));  //判断是否以指定的字符串结尾
        System.out.println(s9.startsWith("hel")); //判断是否以指定的字符串开头
        System.out.println(s9.startsWith("ell",1));//判断是否以指定的字符串在指定的索引开头

        String s10 = "world";
        System.out.println(s9.contains(s10));  //判断字符串中是否包含指定的字符串

        System.out.println(s10.indexOf("rl"));  // 返回指定字符串在当前字符串中的对应的内容的索引
        System.out.println(s10.indexOf("rl",2));//指定开始搜索的位置，返回指定字符串在当前字符串中的对应的内容的索引
        System.out.println(s10.lastIndexOf("r",1)); //false,与上相同，反向搜索

        String str = "没毛病吧，干就完了，奥里给，里给罗";
        System.out.println(str.replace('里','利'));//将字符串中出现的“里” 改成“利”
        System.out.println(str.replace("奥里给","奥利奥"));//将字符串中出现的“奥里给” 改成“奥利奥”

        String str1 = "12343543hea45636klj1asdf234";
        String str2 = str1.replaceAll("\\d+","*").replaceAll("^\\*|\\*$", "");
        System.out.println(str2);//将所有的数字全部转化为“*”，然后将前后的“*”去掉

        String str3 = "1234-45678";
        System.out.println(str3.matches("1234-\\d{4,5}"));//true,是否与指定的正则表达式匹配

        String str4 = "abc-def-ghi-jkl-stv";
        String[] strs = str4.split("-");  //拆分字符串，并返回字符串数组
        System.out.println(strs.length);
    }
}
