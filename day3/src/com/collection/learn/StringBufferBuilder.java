package com.collection.learn;

/**
 * String: String是不可变的字符序列
 * StringBuffer:  可变的字符序列，线程安全，但效率稍低
 * StringBuilder： 可变的字符序列，线程不安全，但效率稍高
 *
 * String str = new String();  // new char[0]
 * StringBuffer sb = new StringBuffer();  //new char[16]
 * StringBuffer sb1 = new stringBuffer("abc")  //new char["abc".length+16]
 * sb1.length(); //3
 * 当StringBuffer的缓存的容量（起始的容量为16）不够存储实际的内容，那么就会扩容底层的数组，默认的情况下，
 * 扩容为原来容量的两倍+2，同时将原有的数组复制到新数组中
 * 开发中，请使用  StringBuffer(int capacity) 直接给容量一个值，够用就行，这样可避免超容复制
 *
 * StringBuffer的额外的常用的方法
 */
public class StringBufferBuilder {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("hello world");
        sb.append(1);   //添加元素，任意类型的
        sb.append("abc").append("ok");
        System.out.println(sb);
        System.out.println(sb.length());  //返回长度

        StringBuffer sb1 = sb.delete(2,4); //  以2为起始位置，4为结束位置，删除元素,返回值与被操做的StringBuffer值一样
        System.out.println(sb);
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer("奥里给");
        System.out.println(sb2.replace(1,2,"利"));//奥利给

        StringBuffer sb3 = new StringBuffer("false");
        System.out.println(sb3.insert(0,"true/"));  //在开头插入字符串

        sb3.reverse();  //将字符串反转
        System.out.println(sb3);

        sb3.reverse();
        String s = sb3.substring(0,4); //没有改变sb3,将指定索引的值返回给s
        System.out.println(sb3);
        System.out.println(s);

        StringBuffer sb5 = new StringBuffer("abco");
        sb5.setCharAt(3,'d');
        System.out.println(sb5);
    }
}
