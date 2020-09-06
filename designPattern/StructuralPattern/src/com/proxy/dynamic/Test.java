package com.proxy.dynamic;

public class Test {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new TeacherImpl());
        Teacher teacher = (Teacher) proxyFactory.getProxyInstance();

        //com.proxy.dynamic.TeacherImpl@65b3120a 和 java.lang.ClassCastException
//        System.out.println(teacher.toString());

        // 代理对象调用方法, 返回代理对象自己
        ( (Teacher)( (Teacher)teacher.teach() ).teach() ).teach();
    }
}
