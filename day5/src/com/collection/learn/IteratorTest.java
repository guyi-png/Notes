package com.collection.learn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 */
public class IteratorTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add("456");
        coll.add(789);
        coll.add(true);

        Iterator iterator =coll.iterator();

//        for (int i=0; i < coll.size(); i++){
//            System.out.println(iterator.next());
//        }
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        iterator = coll.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if ("456".equals(obj)){
                iterator.remove();
            }
        }
        System.out.println(coll);
    }
}

