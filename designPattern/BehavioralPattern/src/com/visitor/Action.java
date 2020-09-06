package com.visitor;

/**
 * 抽象行为父类
 */
public abstract class Action {
    public abstract void getManJudge(Person man);

    public abstract void getHumanJudge(Person woman);
}
