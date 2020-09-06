package com.flyweight;

public class ConcreteWebsite extends Website{
    private String type; // 共享部分，内部状态

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站形式为："+ type+", 使用者是"+user.getName());
    }
}
