package com.proxy.dynamic;

public class TeacherImpl implements Teacher{
    @Override
    public Object teach() {
        System.out.println("被代理的俺给孩儿们上课");
        return "俺是从被代理类的方法中蹦出来的";
    }
}
