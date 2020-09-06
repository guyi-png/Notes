package com.proxy.staticP;

public class Test {
    public static void main(String[] args) {
        TeacherProxy proxy = new TeacherProxy(new TeacherImpl());
        proxy.teach();
    }
}
