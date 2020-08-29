package com.springMVC.dao;

import com.springMVC.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDAO {
    private Map<Integer, Employee> employeeMap;
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    public DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    {
        employeeMap = new HashMap<>();
        employeeMap.put(1, new Employee(1, "AA", 23, departmentDAO.getDepartment(100)));
        employeeMap.put(2, new Employee(2, "BB", 80, departmentDAO.getDepartment(101)));
        employeeMap.put(3, new Employee(3, "CC", 35, departmentDAO.getDepartment(102)));
    }

    public void save(Employee employee){
        employeeMap.put(employee.getId() , employee);
    }

    public Collection<Employee> getAll(){
        return employeeMap.values();
    }

    public Employee get(Integer id){
        return employeeMap.get(id);
    }

    public void delete(Integer id){
        employeeMap.remove(id);
    }

}
