package com.learn.java;

/**
 * 通过jclasslib查看源码的方法的Code中，
 * 杂项可以查到max local variables的值，以及字节码的长度，操作数栈的最大深度。
 * Code下的LineNumberTable中， start pc是字节码对应行号，与之对应的是源码的行号。
 * Code下的LocalVariable中， start pc是局部变量字节码对应行号，与之对应的是其作用的长度
 */
public class LocalVariables {
    public static void main(String[] args) {
        LocalVariables.method();
    }

    public static void method() {
        int k = 19;
        boolean j = true;
        int m = 7;
        System.out.println(k);
    }

    public static void test(){
//        this.method();    this变量不存在于当前方法对应的栈帧的局部变量表中
    }

    public void test1(){
        int k = 891;
        double d = 12.3;
        //slot数为4，包含了this的slot,double占两个slot
    }

    public void test2(){
        {
            int k =6;
            System.out.println(k);
        }

        int a = 78; //k过了作用域，其槽位（slot）会复用给a。
    }
}
