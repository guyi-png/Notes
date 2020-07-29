package com.el.function;

public class ELFunction {
    // el函数会通过tld文件映射到Java类，Java类要public修饰，
    // 调用方法的也需要public修饰且是静态
    public static String concat(String str1, String str2){
        return str1 + str2;  //合并字符串
    }
}
