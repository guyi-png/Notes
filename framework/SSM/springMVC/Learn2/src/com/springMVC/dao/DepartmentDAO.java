package com.springMVC.dao;

import com.springMVC.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDAO {
    private Map<Integer, Department> departmentMap = null;

    {
        departmentMap = new HashMap<>();
        departmentMap.put(100, new Department(100, "olg"));
        departmentMap.put(101, new Department(101, "hut"));
        departmentMap.put(102, new Department(102, "xnb"));
    }

    public Collection<Department> getDepartmentAll(){
        return departmentMap.values();
    }

    public Department getDepartment(int id){
        return departmentMap.get(id);
    }
}
