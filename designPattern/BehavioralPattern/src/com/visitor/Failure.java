package com.visitor;

public class Failure extends Action {

    @Override
    public void getManJudge(Person man) {
        System.out.println("那个男人 " +man.getName()+ "说 你败了");
    }

    @Override
    public void getHumanJudge(Person woman) {
        System.out.println("那个女人 " +woman.getName()+ "说你败了");
    }
}
