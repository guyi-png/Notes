package com.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 具体的迭代器
 */
public class InfoCollegeIterator implements Iterator<Department> {
    private List<Department> departments;// department的存储方式是集合
    private int index = 0;

    public InfoCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {    ///不可破坏原来的数据
        if (index >= departments.size()){
            return false;
        }
        return true;
    }

    @Override
    public Department next() {  ///不可破坏原来的数据
        return departments.get(index++);
    }

    @Override
    public void remove(){}
}
