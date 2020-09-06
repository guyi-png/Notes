package com.iterator;

import java.util.Iterator;

public class ComputerCollege implements College{
    private Department[] departments = new Department[5];
    private int departmentNum = 0;

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departments[departmentNum++] = new Department(name, desc);
    }

    @Override
    public Iterator<Department> createIterator() {
                        // 创建迭代器， 并传入数据, 数据的存储不同，使用不同的迭代器
        return new ComputerCollegeIterator(departments);
    }
}
