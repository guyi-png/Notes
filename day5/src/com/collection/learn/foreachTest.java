package com.collection.learn;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class foreachTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add(97);
        coll.add(true);
        coll.add(12);
        coll.add("hello");
        coll.add(new Object());

        for (Object obj : coll){
            System.out.println(obj);
        }

        System.out.println("*************");
        coll.forEach(System.out::println);

        System.out.println("*************");
        int[] arr = new int[10];
        for (int i : arr){
            i = (int)(Math.random()*100);
            System.out.println(i);
        }


    }
}
