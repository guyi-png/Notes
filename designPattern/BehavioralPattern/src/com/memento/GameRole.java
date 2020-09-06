package com.memento;

/**
 * 需要保存内部状态的类
 */
public class GameRole {
    private int atk;
    private int ac;

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

    // 记录当前状态
    public Memento createMemento(){
        return new Memento(atk, ac);
    }

    // 恢复状态
    public void recoverFromMemento(Memento memento){
        atk = memento.getAtk();
        ac = memento.getAc();
    }

    // 显示状态
    public void display(){
        System.out.println("攻击力: "+atk);
        System.out.println("防御力: "+ac);
    }
}
