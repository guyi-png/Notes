package com.collection.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collections: 操作Collection，map的工具类
 * Collections中常用的静态方法
 *
 */
public class CollectionsTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        List list = new ArrayList();
        list.add(120);
        list.add(119);
        list.add(186);
        list.add(10086);
        list.add(101);
        System.out.println(list);

        Collections.reverse(list);  //倒序
        System.out.println(list);

        Collections.shuffle(list);  //随机乱序
        System.out.println(list);

        Collections.sort(list);  //自然排序
        System.out.println(list);

        Collections.swap(list,1,2); //交换索引为1的元素和索引为2元素的位置
        System.out.println(list);

        list.add(10086);
        System.out.println(Collections.frequency(list,10086)); //元素在数组中出现的频率

        //保证destList的大小不小于srcList的大小
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest,list);  //将原来的list复制到目标list
        System.out.println(dest);

        //返回线程安全的List
        List list1 = Collections.synchronizedList(list);
    }
}
