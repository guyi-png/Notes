package com.set.learn;

import java.util.*;

/**
 * Set接口： 存储无序的，不可重复的
 *          |----HashSet: Set接口的主要实现类，线程不安全，可以存储null
 *              |----LinkedHashSet: HashSet的子类，遍历其内部数据时，可以按照添加的顺序遍历
 *          |----TreeSet:可以按照添加对象的指定属性进行排序
 */
public class SetTest {
    public static void main(String[] args) {
        //Set接口无额外的方法，都是其父接口的方法
        /*
         无序性：不代表随机，以HashSet为例，存储的数据在底层不是按照索引的顺序添加的，
         而是根据数据的hash值决定的
         不可重复性： 保证添加的元素按照equals()判断，不能返回true,相同的元素只能有一个

         以HashSet为例，添加元素时的过程：
            若向HashSet中添加元素a（对象）, 先调用a的类中定义的hashCode()方法，
            计算a元素的hash值，由此hash值通过算法算出a在HashSet底层数组中的存放位置，
            判断存放位置是否有元素，没有则添加到位置上，
            如果有元素（有可能有多个）(设该位置的元素为b)，则比较a，b的hash值，
            不相同则添加到位置上，相同则要比较，通过equals()方法比较，
            还相同(equals方法返回true)则视为重复，不相同（false）则添加到位置上，
            同位置的多个元素以链表存储

         LinkedHashSet在父类HashSet的基础上加了两个引用，用于记录先后添加的顺序，
         以便遍历其内部数据时，可以按照添加的顺序遍历
         */
        Set set = new HashSet();
        set.add("123");
        set.add(54);
        set.add("CSD");
        set.add(new Object());
        set.add(503);
        set.add(503);
        set.add(new Object(){
            @Override
            public String toString() {
                return "object-haha";
            }
        });
        set.add(new Object(){
            @Override
            public String toString() {
                return "object-hehe";
            }
        });

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


        System.out.println("**************************");
        //TreeSet以需要添加的对象类型的compareTo()判断是否添加,若为0则不添加
        TreeSet treeSet = new TreeSet();
        treeSet.add(213);
        treeSet.add(321);
//        treeSet.add("报错");
        treeSet.add(6);
        treeSet.add(-1433);
        iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        TreeSet treeSet1 = new TreeSet(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return 0;
//            }
//        });
    }
}
