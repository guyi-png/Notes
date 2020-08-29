package com.collection.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 一： 集合框架的概述
 * 1.集合,数组都是对多个数据进行存储操作的结构，简称java容器
 * 说明： 此时的存储，主要指的是内存层面的存储，不涉及持久化的存储（硬盘上的文件如xx.txt
 * xx.avi xx.jpg 数据库存储）
 *
 * 2.1 数组在存储多个数据方面的特点和缺点：
 *      * 一旦初始化以后，其长度就确定了，不能修改
 *      * 只能操作指定类型的数据
 *      * 数组中提供的方法非常有限，没有添加，删除，插入等操作
 *      * 对于获取数组中实际元素个数，没有现成的属性或方法可用
 *      * 数组存储数据是有序的元素可重复
 * 二： 集合框架
 *      |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----List接口（动态数组）： 存储有序的，可重复的数据
 *              |----ArrayList, LinkedList, Vector
 *          |----set接口： 存储无序的，不重复的数据
 *      |----Map接口：双列集合，用来存储一对（key-value）的数据 形如x-y 如函数y=f(x)=x^2
 *              |----HashMap, LinkedHashMap, TreeMap, Hashtable, Properties
 */
public class CollectionTest {
    public static void main(String[] args) {
        //注： ArrayList存储有序的，可重复的数据
        //以下为Collection中的方法
        Collection coll = new ArrayList();
        coll.add("aa");
        coll.add(123);
        System.out.println(coll.size());  //2返回有多少个元素

        Collection coll1 = new ArrayList();
        coll1.add(true);

        coll1.addAll(coll);
        System.out.println(coll1.size());

        coll1.clear();
        System.out.println(coll1.isEmpty());  //判断是否为空，无元素

        coll.contains(123); //判断是否包含指定的对象 , 包装类重写了equals()方法
        coll.contains("aa"); //String类重写了equals()方法
        System.out.println(coll.contains(123) +"/"+coll.contains("aa")); //true

        Person p = new Person("aolg",100);
        coll.add(p);
        System.out.println(coll.contains(new Person("aolg",100))); //false,未重写equals方法

        Collection coll2 = Arrays.asList(123,"aa");
        System.out.println(coll.containsAll(coll2)); //true,判断参数coll2中的所有的元素是否都存在当前集合coll中

        //boolean remove(Object o);
        System.out.println(coll.remove(123)); //true,表明元素删除成功, remove会先找元素调用equals方法，然后删除

        System.out.println(coll1.removeAll(coll));// false,从当前集合中移除coll中的所有的元素

        System.out.println(coll1.retainAll(coll)); //false,从当前集合中保留coll中的所有的元素

        System.out.println(coll.equals(coll1)); //false,类型，内容，顺序一样就是true

        System.out.println(coll.hashCode()); //获得对象的哈希值

        Object[] arr = coll.toArray();  //将集合转化为数组
        System.out.println(arr.length);
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}
