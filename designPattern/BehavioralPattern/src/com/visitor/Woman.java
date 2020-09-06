package com.visitor;

public class Woman extends Person{

    public Woman(String name) {
        super(name);
    }

    @Override
    void accept(Action action) {
        action.getHumanJudge(this);
    }
}
