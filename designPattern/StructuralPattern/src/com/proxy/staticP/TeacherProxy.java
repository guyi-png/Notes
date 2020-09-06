package com.proxy.staticP;

public class TeacherProxy implements Teacher {
    private Teacher teacher;

    public TeacherProxy(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void teach() {
        System.out.println("???");
        // 调用被代理类的方法
        teacher.teach();
    }
}
