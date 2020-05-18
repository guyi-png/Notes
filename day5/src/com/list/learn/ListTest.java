package com.list.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  ArrayList,LinkedList,Vector三者的异同：
 *  同: 三类都实现了List接口，存储有序的，可重复的数据
 *  不同：
 *   ArrayList： 作为List接口的主要的实现类，线程不安全，效率高，底层使用Object[] elementData存储
 *   LinkedList: 对于频繁的插入和删除操作，相较于ArrayList效率更高，底层使用双向链表存储
 *   Vector: 作为List接口的古老的实现类。线程安全的，效率第，底层使用Object[] elementData存储
 *
 *          ArrayList jdk7.0源码分析:
 *   ArrayList list = new ArrayList();//创建了长度为10的Object[]数组elementData
 *   List.add("xxx") // 当添加的元素超过底层elementData数组的容量，则扩容，
 *   默认情况下，扩容为原来容量的1.5倍，同时需要将原来的数组赋值到新数组中
 *   建议一开始就大致确定容量，避免反复的复制和抛弃
 *          ArrayList jdk 8.0源码分析:
 *   ArrayList list = new ArrayList(); // 底层Object[] elementData初始化为{}，没有创建长度为10的数组
 *   第一次调用 list.add(xxx)时 底层才创建了长度为10 的数组，并将数据添加到elementData[0], 其他一样
 *
 *          LinkedList的源码分析：
 *   LinkedList list = new LinkedList(); // 内部声明了Node类型的first和last属性，默认值为null，
 *   list.add(xxx)时将数据封装到Node中，创建Node对象
 *
 * Vector就很少用，不多提·
 */
public class ListTest {
    public static void main(String[] args) {
        //List接口的方法
        /*List的常用方法：
            add(Object obj);
            remove(int index);
            set(int index,Object obj);
            add(int index,Object element);
            get(int index);
            size();
            iterator.hasNext()/iterator.next()
            foreach
          */
        ArrayList list = new ArrayList();
        list.add("olg");
        list.add(2);

        System.out.println(list);
        list.add(1,1);  //在索引1出插入值1
        System.out.println(list);

        List list1 =Arrays.asList(3,4,5);
        list.addAll(list1);
        System.out.println(list);

        //Object remove(int index)
        System.out.println(list.remove(0));

        list.set(0,"olg");
        System.out.println(list);

        List subList = list.subList(1,5);
        System.out.println(subList);
    }
}
