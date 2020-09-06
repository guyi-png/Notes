package com.visitor;

public class Success extends Action {
    @Override
    public void getManJudge(Person man) {
        System.out.println("那个男人 " + man.getName()+ "说: 你赢了");
    }

    @Override
    public void getHumanJudge(Person woman) {
        System.out.println("那个女人 "+ woman.getName()+ "说: 你赢了");
    }
}
