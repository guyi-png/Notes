package com.proxy.staticP;

public class TeacherImpl implements Teacher {
    @Override
    public void teach() {
        System.out.println("被代理的俺上课");
    }
}
