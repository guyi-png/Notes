package com.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 保存诸多的状态
 */
public class Caretaker {
    private Memento memento;    // 如果保存一次状态
//    private List<Memento> roleMementos; //对同个角色保存多次状态
//    private HashMap<String, List<Memento>> mementos; // 多个角色保存多个状态


    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}
