package com.iterator;

import java.util.Iterator;

public interface College{
    String getName();

    void addDepartment(String name, String desc);

    // 返回一个迭代器
    Iterator<Department> createIterator();
}
