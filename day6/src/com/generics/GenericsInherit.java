package com.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericsInherit {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        List<Object> list1 = null;
        List<String> list2 = null;
//        list1 = list2;  不行
        List<String> list3= null;
        ArrayList<String> list4 = null;
        list3 = list4;  //可以
    }

    //使用通配符
    public static void test2(){
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("haha");
        list2.add("haah");

        List<?> list = null;
        list = list1;
        list = list2;
//        list.add(?);    不能向通配符修饰的泛型对象中添加数据
        Object obj = list.get(0); //能用通配符的泛型类获取
        System.out.println(obj);
        test3(list1);
        test3(list2);
    }

    public static void test3(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    public static void test4(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = null;
        List<Person> list4 = null;
        List<Object> list5 =null;

        list1 = list3;
        list1 = list4;
//        list1 = list5;
// 可以理解<? extends Person> 为泛型通配符是Person自己或Person的儿子,孙子

//        list2 = list3;
// 可以理解<? super Person> 为泛型通配符是Person自己或Person的爸爸，祖宗
        list2 = list4;
        list2 = list5;

    }
}

class Person{}

class Student extends Person{}
