package com.memento;

import org.w3c.dom.events.EventTarget;

/**
 * 备忘录模式:
 *      memento pattern 是 行为型模式
 *      在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，
 *      这样以后就可以将该对象恢复到原来保存的状态
 *
 *      gameRole    需要保存状态的对象
 *      memento 备忘录， 负责保存好记录，即保存gameRole的内部记录
 *      caretaker n. 看管者；看门人；守护者 adj. 临时代理的， 负责保存多个备忘录对象，使用集合管理，提高效率
 */
public class Client {
    public static void main(String[] args) {
        GameRole gameRole = new GameRole();
        // 设置状态
        gameRole.setAtk(999);
        gameRole.setAc(999);
        // 添加状态到备忘录
        Memento memento = gameRole.createMemento();
        // 把备忘录交到一起管理
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(memento);

        // 改变状态
        gameRole.setAtk(9);
        gameRole.setAc(1);
        // 恢复
       gameRole.recoverFromMemento(caretaker.getMemento());

        // 现在的状态
        gameRole.display();
    }
}
