package com.generics;

import java.util.*;

/**
 * jdk 5.0新增泛型
 * 所谓泛型，就是允许在定义类，接口时通过一个标识表示类中
 * 某个属性的类型或者是某个方法返回值及参数的类型，这个类型参数将在使用时确定
 *
 *自定义泛型： 泛型类，泛型接口，泛型方法
 * 异常类不能声明泛型
 */
public class GenericsTest {
    public static void main(String[] args) {
        test1();
        test2();
        Order<String> order = new Order<>("haha",1,"one");
        order.setOrder("一");
        String str= order.getOrder();
        System.out.println(str);
//        T[] t = new T[];  不行


        String[] strs = new String[]{"aaa","bbbb","cccc","dddd"};
        List<String> list = copyFromArrayToList(strs);  ///调用泛型方法
        System.out.println(list);
    }

    public static void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12);
//        list.add("ad");  只能添加Integer类型的参数
        list.add((int) 'a');
        list.add(666);
        list.add(888);

        for (int ele : list){
            int digit = ele;
            System.out.println(digit);
        }
        System.out.println();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    public static void test2(){
//        Map<String, Integer> map = new HashMap<String, Integer>();
        //jdk7.0 类型推断
        Map<String, Integer> map = new HashMap<>();
        map.put("dagu",1283);
        map.put("yinhe",1824);
        map.put("tailuo",1463);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry= iterator.next();
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("key: " + key + ", value: " + value);
        }
    }

    //自定义泛型方法(可以声明为static)
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for (E ele : arr){
            list.add(ele);
        }
        return list;
    }
}

//自定义泛型类
class Order<T>{
    String name;
    int orderId;
    T orderT;

    public Order(){}

    public Order(String name , int orderId, T orderT){
        this.name = name;
        this.orderT = orderT;
        this.orderId = orderId;
    }

    public T getOrder(){
        return orderT;
    }

    public void setOrder(T orderT){
        this.orderT = orderT;
    }

//    public static void show(){
//        System.out.println(orderT);   //静态方法不能通用泛型结构
//    }
}

class SubOrder<T1,T2> extends Order{}  //等价于class SubOrder<T1,T2> extends Order<Object>{}