package com.visitor;

public class Man extends Person{
    public Man(String name) {
        super(name);
    }

    @Override
    void accept(Action action) {
        action.getManJudge(this);
    }
}
