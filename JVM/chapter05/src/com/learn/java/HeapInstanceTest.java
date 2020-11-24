package com.learn.java;

import java.util.ArrayList;
import java.util.List;

public class HeapInstanceTest {

    public static void main(String[] args) {
        List<HeapInstanceTest> list = new ArrayList<>();

        while(true){
            list.add(new HeapInstanceTest());
        }
    }
}
