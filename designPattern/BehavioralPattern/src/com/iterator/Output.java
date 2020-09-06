package com.iterator;

import java.util.Iterator;
import java.util.List;

public class Output {
    private List<College> colleges;  //学院的集合;

    public Output(List<College> colleges){
        this.colleges = colleges;
    }

    // 打印单个学院的系
    public void printDepartment(Iterator<Department> iterator){
        while (iterator.hasNext()){
            Department department = iterator.next();
            System.out.println(department.getName());
        }
    }

    // 打印所有学院的所有系
    public void printCollege(){
        Iterator<College> collegeIterator = colleges.iterator();  //学院的迭代器，list自己实现的
        while (collegeIterator.hasNext()){
            College college = collegeIterator.next();
            System.out.println("------"+ college.getName() +"-------");
            Iterator<Department> departmentIterator = college.createIterator();//通过自定义获得迭代器

            printDepartment(departmentIterator);
        }
    }
}
