package com.iterator;

import java.util.Iterator;

/**
 * 具体的迭代器
 */
public class ComputerCollegeIterator implements Iterator<Department> {
    private Department[] departments; // department的存储方式是数组
    private int position = 0;  // 遍历的位置

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    // 判断有没有下一个元素
    @Override
    public boolean hasNext() {
        if (position >= departments.length || departments[position] ==  null){
            return false;
        }
        return true;
    }

    // 返回洗一个元素
    @Override
    public Department next() {
        return departments[position++];
    }

    @Override
    public void remove() {}
}
