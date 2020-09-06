package com.templateMethod;

public class PureSoyaMilk extends SoyaMilk{

    @Override
    protected void add() {}

    @Override
    protected boolean isPure() {
        return true;
    }
}
