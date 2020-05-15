package com.guyi.learn;

/**
 * String 类 的 使final用
 *  1.String声明为final的，不可被继承
 *  2.String实现了Serializable接口，表示字符串是支持序列化的，
 *          实现了Comparable接口，表示String可以比较大小
 *  3.String内部定义了final char[] value用于存储字符串数据
 *  4.String代表不可变的字符序列，即不可变性
 *  5.通过字面量的方式给一个字符串赋值，此时的字符串值声明在字符串常量池中，
 *  字符串常量池中是不会存储相同内容的字符串的
 *
 *  String的实例化的方式：
 *      一： 通过字面量定义的方式
 *      二： 通过new String(xxx)
 *
 *      结论：1.常量与常量的拼接结果在常量池中，且常量池中不会存在相同内容的常量
 *           2.只要其中有一个变量，结果就在堆中
 *           3.如果拼接的结果调用intern()方法，返回值就在常量池中
 */
public class StringTest {
    public static void main(String[] args) {
        test1();
        test2();
        System.out.println("************************************");
        test3();
    }

    public static void test1(){
        String s1 = "abc";  // "xxx"是C-风格的字符串字面量
        s1  = "hello";
        String s2 = "dgs";
        s2 = "hello";
        System.out.println(s1 == s2);
        s2 += "world";
        String s3 = s1.replace('h', 'g');
        System.out.println(s3);
    }

    public static void test2(){
        String s1 = "java";
        String s2 = "java"; //两者地址一样，都在方法区中的字符串常量池中

        String s3 = new String("java");
        String s4 = new String("java");
        // 上面的两个在heap中各自开辟内存区，
        // 且内存区中有变量value引用了方法区中的字符串常量池中的字面量"java"的地址
        // 即该变量引用了s1,s2的地址

        Language p1 = new Language("java");
        Language p2 = new Language("java");
        System.out.println(p1.name.equals(p2.name)); //true
        System.out.println(p1.name == p2.name); //true
        System.out.println(p1.name == s1);  //true
    }

    public static void test3(){
        String s1 = "java";
        String s2 = "script";
        String s3 = "javascript";
        String s4 = "java" + "script";
        String s5 = s1 + "script";
        String s6 = "java" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); //true
        System.out.println(s3 == s5); //false
        System.out.println(s3 == s6); //false
        System.out.println(s3 == s7); //false
        System.out.println(s5 == s6); //false
        System.out.println(s6 == s7); //false
        System.out.println(s6 == s7); //false

        String s8 = s3.intern();
//这里s3.intern()返回值赋给s8，s8得到的值会声明在常量池中，而常量池中有"javascript"这个字面量
        //或者  s3.intern()方法将常量池中对应的地址赋给s8  ???
        System.out.println(s8 == s3);
    }
}

class Language{
    public Language(String name){
        this.name = name;
    }

    String name;
}
