package com.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * DAO:data access object
 */
public class DAO<T> {
    private Map<String, T> map;
    //添
    public void add(String id, T entity){
        //xxxxx
        map.put(id,entity);
    }

    // 删
    public void remove(String id){
        //xxxxx
        map.remove(id);
    }

    //改
    public void update(String id, T entity){
        if (map.containsKey(id)){
            map.put(id,entity);
        }
    }

    //查
    public T get(String id){
        return map.get(id);
    }
    public List<T> getForList(){
        Collection<T> values = map.values();
        List<T> list = new ArrayList<>();
        for (T value : values){
            list.add(value);
        }
        return list;
    }
}

class CustomerDAO extends DAO<CustomerDAO>{

}

class StudentDAO extends DAO<StudentDAO>{

}