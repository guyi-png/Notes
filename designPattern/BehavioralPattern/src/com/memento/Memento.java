package com.memento;

public class Memento {
    private int atk;  //攻击力
    private int ac;   //防御力

    public Memento(int atk, int ac) {
        this.atk = atk;
        this.ac = ac;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }
}
