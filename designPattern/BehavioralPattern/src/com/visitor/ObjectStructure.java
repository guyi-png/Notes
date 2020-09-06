package com.visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
    // 有一群人
    private List<Person> persons = new ArrayList<>();

    // 可以有人参加
    public void attach(Person person){
        persons.add(person);
    }

    // 可以移除
    public void detach(Person person){
        persons.remove(person);
    }

    // 显示结果, person可男可女， action可success可failure
    public void displayResult(Action... actions){
        for (Action action : actions){
            for(Person person : persons){

                if (person instanceof Woman)
                    person.accept(action);
                if (person instanceof Man)
                    action.getManJudge(person);

            }
        }
    }
}
